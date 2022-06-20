package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import team10.app.dto.DailyEarningsDto;
import team10.app.dto.EarningsReportDto;
import team10.app.dto.IndividualEarningsDto;
import team10.app.dto.ReservationDto;
import team10.app.model.BusinessClient;
import team10.app.model.RentalEntity;
import team10.app.model.Reservation;
import team10.app.model.ReservationStatus;
import team10.app.repository.ReservationRepository;
import team10.app.util.DateTimeUtil;
import team10.app.util.Sorting;
import team10.app.util.Validator;
import team10.app.util.exceptions.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BusinessClientService businessClientService;
    private final Validator validator;


    public Page<Reservation> getAllReservationsByOwnerPage(String email, String sort, int page, int size) {
        BusinessClient businessClient = businessClientService.getByEmail(email);
        Pageable paging = PageRequest.of(page, size, Sort.by(getSort(sort)));
        return reservationRepository.findByBusinessClient(businessClient, paging);

    }

    private List<Order> getSort(String sort) {
        List<Order> orders = new ArrayList<>();
        String[] sortTokens = sort.split(",");
        switch (sortTokens[0]) {
            case "rentalEntityTitle":
                sortTokens[0] = "rentalEntity.title";
                break;
            case "clientEmail":
                    sortTokens[0] = "client.email";
                    break;
            default:
                break;
        }
        orders.add(Sorting.getSorting(sortTokens));
        return orders;
    }

    public List<ReservationDto> getReservationDtoList(List<Reservation> reservations) {
        for (Reservation r : reservations)
            r.updateStatus();
        return reservations.stream().map(ReservationDto::new).collect(Collectors.toList());
    }

    public Reservation getReservationById(UUID id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    public void updateStatus(UUID id, ReservationStatus status) {
        reservationRepository.updateStatus(status, id);
    }

    public ReservationDto getReservationDtoById(String email, UUID id) {
        Reservation r = this.getReservationById(id);
        if (!validator.validateReservationBusinessClient(r.getBusinessClient().getEmail(), email))
            throw new InvalidReservationBusinessClientException(id, email);
        if (r.getStatus() != ReservationStatus.FINISHED)
            throw new ReservationNotAvailableForReviewException(id);
        return new ReservationDto(r);
    }

    public EarningsReportDto getEarningsReport(String email, long fromDate, long toDate) {
        if (!validator.validateEarningsReportDateRange(fromDate, toDate))
            throw new EarningsReportDateRangeInvalidException(fromDate, toDate);
        return buildEarningsReportDto(getAllReservationsByOwnerInRange(email, fromDate, toDate), fromDate, toDate);

    }

    private List<Reservation> getAllReservationsByOwnerInRange(String email, long fromDate, long toDate) {
        return reservationRepository.getAllReservationsByOwnerInRange(businessClientService.getByEmail(email), fromDate, toDate);
    }

    private EarningsReportDto buildEarningsReportDto(List<Reservation> reservations, long fromDate, long toDate) {
        Map<UUID, IndividualEarningsDto> individualEarningsDtoMap = new HashMap<>();
        Map<Long, DailyEarningsDto> dailyEarningsDtoMap = new HashMap<>();
        for (Reservation reservation : reservations) {
            addIndividualEarnings(individualEarningsDtoMap, reservation, reservation.getRentalEntity()); // Adds earnings for each individual rental entity
            addDailyEarnings(dailyEarningsDtoMap, reservation.getEndDate(), reservation.getEarnings());
        }
        return new EarningsReportDto(fromDate, toDate, individualEarningsDtoMap.values(), dailyEarningsDtoMap.values());
    }

    private void addDailyEarnings(Map<Long, DailyEarningsDto> dailyEarningsDtoMap, long day, double earnings) {
        if (earnings > 0) {
            if (!dailyEarningsDtoMap.containsKey(day))
                dailyEarningsDtoMap.put(day, new DailyEarningsDto(day, earnings));
            else
                dailyEarningsDtoMap.get(day).addEarnings(earnings);
        }
    }

    private void addIndividualEarnings(Map<UUID, IndividualEarningsDto> individualEarningsDtoMap, Reservation reservation, RentalEntity rentalEntity) {
        if (reservation.getEarnings() > 0) {
            if (!individualEarningsDtoMap.containsKey(rentalEntity.getId())) {
                individualEarningsDtoMap.put(rentalEntity.getId(),
                        new IndividualEarningsDto(rentalEntity.getId(), rentalEntity.getTitle(), reservation.getEarnings()));
            } else
                individualEarningsDtoMap.get(rentalEntity.getId()).addEarnings(reservation.getEarnings());
        }
    }

    public Map<String, Integer> getReservationsReport(String email, String period) {
        switch (period) {
            case("week"):
                return this.buildWeekReservationsReportMap(this.getAllReservationsByOwnerInRange(email,
                        DateTimeUtil.getDeltaWeekFromToday(-9), DateTimeUtil.getTodayEpochMillisecond()));
            case("month"):
                return this.buildMonthReservationsReportMap(this.getAllReservationsByOwnerInRange(email,
                        DateTimeUtil.getFirstDayOfCurrentYear(), DateTimeUtil.getLastDayOfCurrentYear()));
            case("year"):
                return this.buildYearReservationsReportMap(this.getAllReservationsByOwnerInRange(email, 0,
                        DateTimeUtil.getTodayEpochMillisecond()));
            default:
                throw new ReservationsReportParamInvalidException(period);
        }
    }

    private Map<String, Integer> buildWeekReservationsReportMap(List<Reservation> reservations) {
        Map<String, Integer> map = new HashMap<>();
        for (Reservation reservation : reservations) {
            String week = DateTimeUtil.getWeekOfYearFromDate(reservation.getEndDate());
            if (!map.containsKey(week))
                map.put(week, 0);
            map.put(week, map.get(week) + 1);
        }
        return map;
    }

    private Map<String, Integer> buildMonthReservationsReportMap(List<Reservation> reservations) {
        Map<String, Integer> map = new HashMap<>();
        for (Reservation reservation : reservations) {
                String month = DateTimeUtil.getMonthFromDate(reservation.getEndDate());
                if (!map.containsKey(month))
                    map.put(month, 0);
                map.put(month, map.get(month) + 1);
        }
        return map;
    }

    private Map<String, Integer> buildYearReservationsReportMap(List<Reservation> reservations) {
        Map<String, Integer> map = new HashMap<>();
        for (Reservation reservation : reservations) {
            String year = DateTimeUtil.getYearFromDate(reservation.getEndDate());
            if (!map.containsKey(year))
                map.put(year, 0);
            map.put(year, map.get(year) + 1);
        }
        return map;
    }
}
