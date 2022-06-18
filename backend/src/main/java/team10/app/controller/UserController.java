package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.PasswordChangeDto;
import team10.app.security.auth.AuthUtil;
import team10.app.security.auth.JWTProvider;
import team10.app.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthUtil authUtil;

    @Transactional
    @PutMapping("/update/email")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR', 'MAIN_ADMIN', 'ADMIN')")
    public ResponseEntity<String> updateEmail(@RequestBody String email, @RequestHeader(name = "Authorization") String token)
    {
        try {
            userService.updateEmail(email, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/password")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR', 'MAIN_ADMIN', 'ADMIN')")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordChangeDto passwordChangeDto, @RequestHeader(name = "Authorization") String token)
    {
        try {
            userService.updatePassword(passwordChangeDto, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/firstname")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR', 'MAIN_ADMIN', 'ADMIN')")
    public ResponseEntity<String> updateFirstName(@RequestBody String firstName, @RequestHeader(name = "Authorization") String token)
    {
        try {
            userService.updateFirstName(firstName, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(firstName, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/lastname")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR', 'MAIN_ADMIN', 'ADMIN')")
    public ResponseEntity<String> updateLastName(@RequestBody String lastName, @RequestHeader(name = "Authorization") String token)
    {
        try {
            userService.updateLastName(lastName, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(lastName, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/phone-number")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR', 'MAIN_ADMIN', 'ADMIN')")
    public ResponseEntity<String> updatePhoneNumber(@RequestBody String phoneNumber, @RequestHeader(name = "Authorization") String token)
    {
        try {
            userService.updatePhoneNumber(phoneNumber, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(phoneNumber, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/delete")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR', 'CLIENT')")
    public ResponseEntity<HttpStatus> requestDeletion(@RequestBody String deletionReason, @RequestHeader(name = "Authorization") String token)
    {
        try {
            userService.requestDeletion(deletionReason, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
