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
import team10.app.dto.PasswordChangeDto;
import team10.app.security.auth.JWTProvider;
import team10.app.service.BusinessClientService;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/business-client")
@AllArgsConstructor
public class BusinessClientController {

    private final BusinessClientService businessClientService;
    private final JWTProvider jwtProvider;

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

    @Transactional
    @PutMapping("/update/firstname")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateFirstName(@RequestBody String firstName, @RequestHeader(name = "Authorization") String token)
    {
        try {
            businessClientService.updateFirstName(firstName, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(firstName, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/lastname")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateLastName(@RequestBody String lastName, @RequestHeader(name = "Authorization") String token)
    {
        try {
            businessClientService.updateLastName(lastName, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lastName, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/phone-number")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updatePhoneNumber(@RequestBody String phoneNumber, @RequestHeader(name = "Authorization") String token)
    {
        try {
            businessClientService.updatePhoneNumber(phoneNumber, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(phoneNumber, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PutMapping("/update/address")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto, @RequestHeader(name = "Authorization") String token)
    {
        try {
            businessClientService.updateAddress(addressDto, jwtProvider.getAuthentication(token.substring(7)).getName());
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
            businessClientService.updateDateOfBirth(dateOfBirth, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dateOfBirth, HttpStatus.OK);
    }


    @Transactional
    @PutMapping("/update/email")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateEmail(@RequestBody String email, @RequestHeader(name = "Authorization") String token)
    {
        try {
            businessClientService.updateEmail(email, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/password")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordChangeDto passwordChangeDto, @RequestHeader(name = "Authorization") String token)
    {
        try {
            businessClientService.updatePassword(passwordChangeDto, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
