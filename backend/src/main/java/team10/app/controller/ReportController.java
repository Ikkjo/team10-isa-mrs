package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ReportDto;
import team10.app.model.DeletionRequest;
import team10.app.model.Report;
import team10.app.security.auth.JWTProvider;
import team10.app.service.AdminService;
import team10.app.service.ReportService;
import team10.app.service.UserService;
import team10.app.util.exceptions.ReportNotFoundException;
import team10.app.util.exceptions.ReviewInvalidException;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
public class ReportController {

    private final AdminService adminService;
    private final ReportService reportService;
    private final JWTProvider jwtProvider;

    @GetMapping(path = "/not-reviewed")
    @PreAuthorize("hasAnyRole('MAIN_ADMIN', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> getNotReviewedReports(
            @RequestParam(defaultValue = "id,desc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<Report> reportPage = reportService.getNotReviewedReports(sort, page, size);
            Map<String, Object> response = new HashMap<>();
            response.put(
                    "reservations", reportService.getReportDtoList(reportPage.getContent())
            );
            response.put("currentPage", reportPage.getNumber());
            response.put("totalItems", reportPage.getTotalElements());
            response.put("totalPages", reportPage.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/{reservationId}/didnt-arrive")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<HttpStatus> addClientDidntArrive(Principal principal, @PathVariable UUID reservationId) {
        try {
            reportService.addClientDidntArrive(principal.getName(), reservationId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PostMapping("/{reservationId}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<ReportDto> addReport(
            @RequestHeader (name="Authorization") String token,
            @PathVariable UUID reservationId,
            @RequestBody ReportDto reportDto
    ) {
        try {
            return new ResponseEntity<>(
                    reportService.addReport(
                        jwtProvider.getAuthentication(token.substring(7)).getName(), reservationId, reportDto
                    ),
                    HttpStatus.OK
            );
        }
        catch (ReviewInvalidException | UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}/penalize")
    @PreAuthorize("hasAnyRole('MAIN_ADMIN', 'ADMIN')")
    public ResponseEntity<HttpStatus> penalizeClient(@PathVariable UUID id) {
        try {
            adminService.penalizeClient(id, true);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ReportNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}/dont-penalize")
    @PreAuthorize("hasAnyRole('MAIN_ADMIN', 'ADMIN')")
    public ResponseEntity<HttpStatus> dontPenalizeClient(@PathVariable UUID id) {
        try {
            adminService.penalizeClient(id, false);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ReportNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
