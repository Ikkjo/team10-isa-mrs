package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team10.app.dto.AdminReportDto;
import team10.app.dto.ReportDto;
import team10.app.model.*;
import team10.app.repository.ReportRepository;
import team10.app.util.Sorting;
import team10.app.util.Validator;
import team10.app.util.exceptions.InvalidReservationBusinessClientException;
import team10.app.util.exceptions.ReviewInvalidException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final Validator validator;

    public void addClientDidntArrive(String businessClientEmail, UUID reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (!validator.validateReservationBusinessClient(businessClientEmail, reservation.getBusinessClient().getEmail()))
            throw new InvalidReservationBusinessClientException(reservationId, businessClientEmail);
        reservationService.updateStatus(reservation.getId(), ReservationStatus.DIDNT_ARRIVE);
        clientService.addPenalty(reservation.getClient().getId());
    }

    public ReportDto addReport(String email, UUID reservationId, ReportDto reportDto) {
        if (!validator.validateReportDto(reportDto))
            throw new ReviewInvalidException();
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (reservation.getStatus() != ReservationStatus.FINISHED)
            throw new ReviewInvalidException();
        if (!validator.validateReservationBusinessClient(email, reservation.getBusinessClient().getEmail()))
            throw new InvalidReservationBusinessClientException(reservationId, email);
        Report report = new Report(reportDto);
        reportRepository.saveAndFlush(report);
        return reportDto;
    }

    public Page<Report> getNotReviewedReports(String sort, int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by(reportGetSort(sort)));
        return reportRepository.findAllNotReviewed(paging);
    }

    private List<Sort.Order> reportGetSort(String sort) {
        List<Sort.Order> orders = new ArrayList<>();
        String[] sortTokens = sort.split(",");
        switch (sortTokens[0]) {
            case "reportedByRole":
                sortTokens[0] = "rentalEntity.owner.role";
                break;
            case "reportedBy":
                sortTokens[0] = "rentalEntity.owner.email";
                break;
            case "personReported":
                sortTokens[0] = "client.email";
                break;
            case "rentalEntityTitle":
                sortTokens[0] = "rentalEntity.title";
                break;
            default:
                break;
        }
        orders.add(Sorting.getSorting(sortTokens));
        return orders;
    }

    public List<AdminReportDto> getReportDtoList(List<Report> reports) {
        return reports.stream()
                .map(AdminReportDto::new)
                .collect(Collectors.toList());
    }
}
