package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.*;
import team10.app.model.DeletionRequest;
import team10.app.model.RegistrationRequest;
import team10.app.security.auth.JWTProvider;
import team10.app.service.AdminService;
import team10.app.util.exceptions.EmailTakenException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JWTProvider jwtProvider;

    @GetMapping
    @PreAuthorize("hasAnyRole('MAIN_ADMIN', 'ADMIN', 'UNVERIFIED_ADMIN')")
    public ResponseEntity<AdminDto> getUserDetails(Principal principal) {
        try {
            return ResponseEntity.ok(adminService.getUserDetails(principal.getName()));
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/registration-requests")
    @PreAuthorize("hasAnyRole('ADMIN','MAIN_ADMIN')")
    public ResponseEntity<List<BusinessClientRegistrationRequestNoPasswordDto>> getRegistrationRequests() {
        return ResponseEntity.ok(adminService.getRegistrationRequests());
    }

    @Transactional
    @PutMapping(path = "/registration-request/{id}/accept")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> acceptBusinessClient(@PathVariable UUID id) {
        try {
            adminService.acceptBusinessClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping(path = "/registration-request/{id}/decline")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> declineBusinessClient(@PathVariable UUID id, @RequestBody String declineReason) {
        try {
            adminService.declineBusinessClient(id, declineReason);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PostMapping(path = "/create-admin")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminRegistrationDto adminDto) {
        try {
            return ResponseEntity.ok(adminService.createAdmin(adminDto));
        } catch (UsernameNotFoundException | EmailTakenException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/deletion-requests")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<List<DeletionRequestDto>> getDeletionRequests() {
        return ResponseEntity.ok(adminService.getDeletionRequests());
    }

    @Transactional
    @PutMapping(path = "/deletion-request/{id}/accept")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> acceptDeletionRequest(@PathVariable UUID id, @RequestBody String response) {
        try {
            adminService.acceptDeletionRequest(id, response);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping(path = "/deletion-request/{id}/decline")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> declineDeletionRequest(@PathVariable UUID id, @RequestBody String response) {
        try {
            adminService.declineDeletionRequest(id, response);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping(path = "/verify-admin")
    @PreAuthorize("hasRole('UNVERIFIED_ADMIN')")
    public ResponseEntity<AdminDto> verifyAdmin(@RequestBody String newPassword, @RequestHeader(name = "Authorization") String token){
        try {
            return ResponseEntity.ok(
                    adminService.verifyAdmin(
                            jwtProvider.getAuthentication(token.substring(7)).getName(), newPassword
                    )
            );
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
