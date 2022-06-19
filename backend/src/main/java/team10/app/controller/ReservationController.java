package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ReservationDto;
import team10.app.model.Reservation;
import team10.app.security.auth.AuthUtil;
import team10.app.service.ReservationService;
import team10.app.util.exceptions.InvalidReservationBusinessClientException;
import team10.app.util.exceptions.ReservationNotAvailableForReviewException;
import team10.app.util.exceptions.ReservationNotFoundException;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final AuthUtil authUtil;

    @GetMapping()
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Map<String, Object>> getAllReservations(
            Principal principal,
            @RequestParam(defaultValue = "id,desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<Reservation> reservationPage = reservationService.getAllReservationsByOwnerPage(principal.getName(), sort, page, size);
            Map<String, Object> response = new HashMap<>();
            response.put("reservations", reservationService.getReservationDtoList(reservationPage.getContent()));
            response.put("currentPage", reservationPage.getNumber());
            response.put("totalItems", reservationPage.getTotalElements());
            response.put("totalPages", reservationPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<ReservationDto> getReservationById(@RequestHeader (name="Authorization") String token, @PathVariable UUID id) {
        try {
            return new ResponseEntity<>(
                    reservationService.getReservationDtoById(
                            authUtil.getEmailFromToken(token),
                            id
                    ),
                    HttpStatus.OK
            );
        } catch (InvalidReservationBusinessClientException | ReservationNotAvailableForReviewException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ReservationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
