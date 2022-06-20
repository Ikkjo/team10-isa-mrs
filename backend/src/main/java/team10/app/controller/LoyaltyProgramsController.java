package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.LoyaltyDto;
import team10.app.model.Loyalty;
import team10.app.service.AdminService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/loyalty-programs")
@AllArgsConstructor
public class LoyaltyProgramsController {

    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<List<LoyaltyDto>> getLoyaltyPrograms() {
        try {
            List<Loyalty> loyalties = adminService.getLoyaltyPrograms();
            return new ResponseEntity<>(adminService.getLoyaltyDtoList(loyalties), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<LoyaltyDto> getLoyaltyProgramDetails(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(
                    adminService.getLoyaltyDto(adminService.getLoyaltyProgram(id)),
                    HttpStatus.OK
            );
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
