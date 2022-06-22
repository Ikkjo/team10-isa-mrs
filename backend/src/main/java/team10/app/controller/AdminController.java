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
import team10.app.model.*;
import team10.app.security.auth.AuthUtil;
import team10.app.service.*;
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
    private final UserService userService;
    private final RentalEntityService rentalEntityService;
    private final ClientReviewService clientReviewService;
    private final AuthUtil authUtil;
    private final ClientReportService clientReportService;

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
                    "registrationRequests", adminService.getRegistrationRequestsDtoList(registrationRequestPage.getContent())
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
                    "deletionRequests", adminService.getDeletionRequestsDtoList(deletionRequestPage.getContent())
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
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Map<String, Double>> getReport(@RequestParam long fromDate, @RequestParam long toDate){
        try {
            return ResponseEntity.ok(adminService.getReport(fromDate, toDate)
            );
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/users")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(defaultValue = "id,desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<User> userPage = userService.getAllUsers(sort, page, size);
            Map<String, Object> response = new HashMap<>();
            response.put(
                    "users", userService.getUserDtoList(userPage.getContent())
            );
            response.put("currentPage", userPage.getNumber());
            response.put("totalItems", userPage.getTotalElements());
            response.put("totalPages", userPage.getTotalPages());
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(path = "/users/{id}/toggle-deleted")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> toggleUserDeletedStatus(@PathVariable UUID id) {
        try {
            adminService.toggleUserDeletedStatus(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/rental-entities")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Map<String, Object>> getAllRentalEntities(
            @RequestParam(defaultValue = "id,desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<RentalEntity> rentalEntitiesPage = rentalEntityService.getAllRentalEntities(sort, page, size);
            Map<String, Object> response = new HashMap<>();
            response.put(
                    "rentalEntities", adminService.getRentalEntityDtoList(rentalEntitiesPage.getContent())
            );
            response.put("currentPage", rentalEntitiesPage.getNumber());
            response.put("totalItems", rentalEntitiesPage.getTotalElements());
            response.put("totalPages", rentalEntitiesPage.getTotalPages());
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(path = "/rental-entities/{id}/toggle-deleted")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> toggleRentalEntityDeletedStatus(@PathVariable UUID id) {
        try {
            adminService.toggleRentalEntityDeletedStatus(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/reviews")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<List<ClientReviewDto>> getClientReviews() {
        try {
            List<ClientReview> clientReviews = clientReviewService.getClientReviews();
            return ResponseEntity.ok(clientReviewService.getClientReviewDtoList(clientReviews));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(path = "/reviews/{id}/accept")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> acceptClientReview(@PathVariable UUID id) {
        try {
            adminService.acceptClientReview(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(path = "/reviews/{id}/decline")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> declineClientReview(@PathVariable UUID id) {
        try {
            adminService.declineClientReview(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/client-reports")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<List<ClientReportDto>> getClientReports() {
        try {
            List<ClientReport> clientReports = clientReportService.getClientReports();
            return ResponseEntity.ok(clientReportService.getClientReportDtoList(clientReports));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(path = "/client-reports/{id}/accept")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> acceptClientReport(@PathVariable UUID id, @RequestBody String response) {
        try {
            adminService.acceptClientReport(id, response);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping(path = "/client-reports/{id}/decline")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<HttpStatus> declineClientReport(@PathVariable UUID id, @RequestBody String response) {
        try {
            adminService.declineClientReport(id, response);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
