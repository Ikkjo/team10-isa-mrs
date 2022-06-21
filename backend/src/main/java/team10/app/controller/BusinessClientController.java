package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.AddressDto;
import team10.app.dto.BusinessClientDto;
import team10.app.dto.EarningsReportDto;
import team10.app.dto.RentalEntityRatingDto;
import team10.app.security.auth.AuthUtil;
import team10.app.service.BusinessClientService;
import team10.app.service.RatingService;
import team10.app.service.ReservationService;
import team10.app.util.exceptions.EarningsReportDateRangeInvalidException;
import team10.app.util.exceptions.ReservationsReportParamInvalidException;

import java.security.Principal;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/v1/business-client")
@AllArgsConstructor
public class BusinessClientController {

    private final BusinessClientService businessClientService;
    private final ReservationService reservationService;
    private final RatingService ratingService;
    private final AuthUtil authUtil;

    @GetMapping
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<BusinessClientDto> getUserDetails(Principal principal) {
        try {
            return new ResponseEntity<>(businessClientService.getUserDetails(principal.getName()), HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PutMapping("/update/address")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto, @RequestHeader(name = "Authorization") String token)
    {
        try {
            businessClientService.updateAddress(addressDto, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/date-of-birth")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateDateOfBirth(@RequestBody String dateOfBirth, @RequestHeader(name = "Authorization") String token)
    {
        try {
            businessClientService.updateDateOfBirth(dateOfBirth, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dateOfBirth, HttpStatus.OK);
    }

    @GetMapping("/report/earnings")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<EarningsReportDto> getEarningsReport(@RequestHeader(name = "Authorization") String token,
                                                               @RequestParam long fromDate, @RequestParam long toDate)
    {
        try {
            return new ResponseEntity<>(reservationService.getEarningsReport(authUtil.getEmailFromToken(token), fromDate, toDate), HttpStatus.OK);
        }
        catch (EarningsReportDateRangeInvalidException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/report/reservations")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Map<String, Integer>> getEarningsReport(@RequestHeader(name = "Authorization") String token,
                                                                 @RequestParam String period)
    {
        try {
            return new ResponseEntity<>(reservationService.getReservationsReport(authUtil.getEmailFromToken(token), period), HttpStatus.OK);
        }
        catch (ReservationsReportParamInvalidException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/report/rating")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<List<RentalEntityRatingDto>> getRatingReport(@RequestHeader(name = "Authorization") String token)
    {
        try {
            return new ResponseEntity<>(ratingService.getRatingReport(authUtil.getEmailFromToken(token)), HttpStatus.OK);
        }
        catch (ReservationsReportParamInvalidException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
