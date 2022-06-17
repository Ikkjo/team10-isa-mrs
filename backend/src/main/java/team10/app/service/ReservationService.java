package team10.app.service;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.FilterDef;
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
        return reservations.stream().map(r ->
                new ReservationDto(r.getId(), r.getStartDate(), r.getEndDate(), r.getPrice(), r.getStatus(),
                        r.getClient().getEmail(), r.getBusinessClient().getId(), r.getBusinessClient().getEmail(),
                        r.getRentalEntity().getId(), r.getRentalEntity().getTitle())).collect(Collectors.toList());
    }
}
