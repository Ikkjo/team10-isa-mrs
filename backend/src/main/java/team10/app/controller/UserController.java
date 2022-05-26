package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.PasswordChangeDto;
import team10.app.security.auth.JWTProvider;
import team10.app.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTProvider jwtProvider;

    @Transactional
    @PutMapping("/update/email")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateEmail(@RequestBody String email, @RequestHeader(name = "Authorization") String token)
    {
        try {
            userService.updateEmail(email, jwtProvider.getAuthentication(token.substring(7)).getName());
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
            userService.updatePassword(passwordChangeDto, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/firstname")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateFirstName(@RequestBody String firstName, @RequestHeader(name = "Authorization") String token)
    {
        try {
            userService.updateFirstName(firstName, jwtProvider.getAuthentication(token.substring(7)).getName());
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
            userService.updateLastName(lastName, jwtProvider.getAuthentication(token.substring(7)).getName());
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
            userService.updatePhoneNumber(phoneNumber, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(phoneNumber, HttpStatus.OK);
    }

}