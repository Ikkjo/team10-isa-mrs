package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.ReviewDto;
import team10.app.model.*;
import team10.app.repository.ReviewRepository;
import team10.app.util.Validator;
import team10.app.util.exceptions.InvalidReservationBusinessClientException;
import team10.app.util.exceptions.ReviewInvalidException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final UserService userService;
    private final Validator validator;


    public void addClientDidntArrive(String businessClientEmail, UUID reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (!validator.validateReservationBusinessClient(businessClientEmail, reservation.getBusinessClient().getEmail()))
            throw new InvalidReservationBusinessClientException(reservationId, businessClientEmail);
        reservationService.updateStatus(reservation.getId(), ReservationStatus.DIDNT_ARRIVE);
        clientService.addPenalty(reservation.getClient().getId());
    }

    public ReviewDto addReview(String email, UUID reservationId, ReviewDto reviewDto) {
        if (!validator.validateReviewDto(reviewDto))
            throw new ReviewInvalidException();
        Reservation reservation = reservationService.getReservationById(reservationId);
        User user = userService.loadUserByUsername(email);
        if (user.getRole().equals(UserRole.CLIENT)) {
            // TODO: Add review logic
        }
        else {
            if (!validator.validateReservationBusinessClient(email, reservation.getBusinessClient().getEmail()))
                throw new InvalidReservationBusinessClientException(reservationId, email);
            Review review = new Review(reviewDto.getMessage(), reviewDto.isPenalize());
            reviewRepository.saveAndFlush(review);
        }
        return reviewDto;
    }
}
