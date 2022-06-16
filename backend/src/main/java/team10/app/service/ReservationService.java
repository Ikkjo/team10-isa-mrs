package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import team10.app.dto.ReservationDto;
import team10.app.model.BusinessClient;
import team10.app.model.Reservation;
import team10.app.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BusinessClientService businessClientService;


    public Page<Reservation> getAllReservationsByOwner(String email, String[] sort, int page, int size) {
        BusinessClient businessClient = businessClientService.getByEmail(email);
        Pageable paging = PageRequest.of(page, size, Sort.by(getSort(sort)));
        return reservationRepository.findByBusinessClient(businessClient, paging);

    }

    private List<Order> getSort(String[] sort) {
        List<Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }
        return orders;
    }

    private Sort.Direction getSortDirection(String s) {
        return s.contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    public List<ReservationDto> getReservationDtoList(List<Reservation> reservations) {
        return reservations.stream().map(r ->
                new ReservationDto(r.getId(), r.getStartDate(), r.getEndDate(), r.getPrice(), r.getStatus(),
                        r.getClient().getEmail(), r.getBusinessClient().getEmail(),
                        r.getRentalEntity().getId(), r.getRentalEntity().getTitle())).collect(Collectors.toList());
    }
}
