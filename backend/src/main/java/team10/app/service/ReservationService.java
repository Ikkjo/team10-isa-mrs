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
import team10.app.model.ReservationStatus;
import team10.app.repository.ReservationRepository;
import team10.app.util.Validator;
import team10.app.util.exceptions.InvalidReservationBusinessClientException;
import team10.app.util.exceptions.ReservationNotAvailableForReviewException;
import team10.app.util.exceptions.ReservationNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BusinessClientService businessClientService;
    private final Validator validator;


    public Page<Reservation> getAllReservationsByOwner(String email, String sort, int page, int size) {
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
        orders.add(new Order(getSortDirection(sortTokens[1]), sortTokens[0]));
        return orders;
    }

    private Sort.Direction getSortDirection(String s) {
        return s.contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
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
}
