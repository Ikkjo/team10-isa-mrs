package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ClientDto;
import team10.app.dto.CreateReservationDto;
import team10.app.dto.MakeReservationDto;
import team10.app.dto.ReservationDto;
import team10.app.security.auth.AuthUtil;
import team10.app.security.auth.JWTProvider;
import team10.app.service.ClientService;
import team10.app.service.ReservationService;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ReservationService reservationService;
    private final AuthUtil authUtil;
    private final JWTProvider jwtProvider;

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ClientDto> getUserDetails(Principal principal) {
        try {
            return new ResponseEntity<>(clientService.getUserDetails(principal.getName()), HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping(path = "/make-reservation")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> makeReservation(@RequestHeader (name="Authorization") String token,
                                                  @RequestBody MakeReservationDto reservationDto) {
        try {
            reservationDto.setClientEmail(authUtil.getEmailFromToken(token));
            reservationService.makeReservation(reservationDto);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
