package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.*;
import team10.app.model.DeletionRequest;
import team10.app.model.Loyalty;
import team10.app.model.RegistrationRequest;
import team10.app.security.auth.AuthUtil;
import team10.app.service.AdminService;
import team10.app.util.exceptions.EmailTakenException;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AuthUtil authUtil;

    @GetMapping
    @PreAuthorize("hasAnyRole('MAIN_ADMIN', 'ADMIN', 'UNVERIFIED_ADMIN')")
    public ResponseEntity<AdminDto> getUserDetails(Principal principal) {
        try {
            return ResponseEntity.ok(adminService.getUserDetails(principal.getName()));
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/registration-requests")
    @PreAuthorize("hasAnyRole('ADMIN','MAIN_ADMIN')")
    public ResponseEntity<Map<String, Object>> getRegistrationRequests(
            @RequestParam(defaultValue = "id,desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<RegistrationRequest> registrationRequestPage = adminService.getRegistrationRequests(sort, page, size);
            Map<String, Object> response = new HashMap<>();
            response.put(
                    "reservations", adminService.getRegistrationRequestsDtoList(registrationRequestPage.getContent())
            );
            response.put("currentPage", registrationRequestPage.getNumber());
            response.put("totalItems", registrationRequestPage.getTotalElements());
            response.put("totalPages", registrationRequestPage.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(path = "/registration-request/{id}/accept")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> acceptBusinessClient(@PathVariable UUID id) {
        try {
            adminService.acceptBusinessClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
        catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PostMapping(path = "/create-admin")
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminRegistrationDto adminDto) {
        try {
            return ResponseEntity.ok(adminService.createAdmin(adminDto));
        }
        catch (UsernameNotFoundException | EmailTakenException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/deletion-requests")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Map<String, Object>> getDeletionRequests(
            @RequestParam(defaultValue = "id,desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<DeletionRequest> deletionRequestPage = adminService.getDeletionRequests(sort, page, size);
            Map<String, Object> response = new HashMap<>();
            response.put(
                    "reservations", adminService.getDeletionRequestsDtoList(deletionRequestPage.getContent())
            );
            response.put("currentPage", deletionRequestPage.getNumber());
            response.put("totalItems", deletionRequestPage.getTotalElements());
            response.put("totalPages", deletionRequestPage.getTotalPages());
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
        catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
        catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(path = "/verify-admin")
    @PreAuthorize("hasRole('UNVERIFIED_ADMIN')")
    public ResponseEntity<AdminDto> verifyAdmin(@RequestBody String newPassword, @RequestHeader(name = "Authorization") String token){
        try {
            return ResponseEntity.ok(
                    adminService.verifyAdmin(
                            authUtil.getEmailFromToken(token), newPassword
                    )
            );
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/report")
    @PreAuthorize("hasRole('UNVERIFIED_ADMIN')")
    public ResponseEntity<Map<Object, Double>> getReport(@RequestParam long fromDate, @RequestParam long toDate){
        try {
            return ResponseEntity.ok(adminService.getReport(fromDate, toDate)
            );
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
