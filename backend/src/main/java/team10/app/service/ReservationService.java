package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team10.app.dto.ReservationDto;
import team10.app.model.BusinessClient;
import team10.app.model.Reservation;
import team10.app.repository.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BusinessClientService businessClientService;


    public Page<Reservation> getAllReservationsByOwner(String email, int page, int size) {
        BusinessClient businessClient = businessClientService.getByEmail(email);
        Pageable paging = PageRequest.of(page, size);
        return reservationRepository.findByBusinessClient(businessClient, paging);

    }

    public List<ReservationDto> getReservationDtoList(List<Reservation> reservations) {
        return reservations.stream().map(r ->
                new ReservationDto(r.getId(), r.getStartDate(), r.getEndDate(), r.getPrice(), r.getStatus(),
                        r.getClient().getEmail(), r.getBusinessClient().getEmail(),
                        r.getRentalEntity().getId(), r.getRentalEntity().getTitle())).collect(Collectors.toList());
    }
}
