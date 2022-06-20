package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.LoyaltyDto;
import team10.app.model.Loyalty;
import team10.app.service.LoyaltyProgramsService;
import team10.app.util.exceptions.LoyaltyAlreadyExistsException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/loyalty-programs")
@AllArgsConstructor
public class LoyaltyProgramsController {

    private final LoyaltyProgramsService loyaltyProgramsService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<List<LoyaltyDto>> getLoyaltyPrograms() {
        try {
            List<Loyalty> loyalties = loyaltyProgramsService.getLoyaltyPrograms();
            return new ResponseEntity<>(loyaltyProgramsService.getLoyaltyDtoList(loyalties), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<LoyaltyDto> getLoyaltyProgramDetails(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(
                    loyaltyProgramsService.getLoyaltyDto(loyaltyProgramsService.getLoyaltyProgram(id)),
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

    @Transactional
    @PutMapping(path = "/update/{id}/title")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<String> updateLoyaltyTitle(@PathVariable UUID id, @RequestBody String title) {
        try {
            loyaltyProgramsService.updateTitle(id, title);
            return ResponseEntity.ok(title);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/available-at-points/{points}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Integer> updateLength(
            @PathVariable(name = "id") UUID id,
            @PathVariable(name = "points") int points
    ) {
        try {
            loyaltyProgramsService.updateAvailableAtPoints(id, points);
            return ResponseEntity.ok(points);
        }
        catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/client-discount/{percentage}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Double> updateClientDiscount(
            @PathVariable(name = "id") UUID id,
            @PathVariable(name = "percentage") double percentage
    ) {
        try {
            loyaltyProgramsService.updateClientDiscount(id, percentage);
            return ResponseEntity.ok(percentage);
        }
        catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/business-client-cut/{percentage}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Double> updateBusinessClientCut(
            @PathVariable(name = "id") UUID id,
            @PathVariable(name = "percentage") double percentage
    ) {
        try {
            loyaltyProgramsService.updateBusinessClientCut(id, percentage);
            return ResponseEntity.ok(percentage);
        }
        catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/business-client-points-per-reservation/{points}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Integer> updateBusinessClientPointsPerReservation(
            @PathVariable(name = "id") UUID id,
            @PathVariable(name = "points") int points
    ) {
        try {
            loyaltyProgramsService.updateBusinessClientPointsPerReservation(id, points);
            return ResponseEntity.ok(points);
        }
        catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/client-points-per-reservation/{points}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<Integer> updateClientPointsPerReservation(
            @PathVariable(name = "id") UUID id,
            @PathVariable(name = "points") int points
    ) {
        try {
            loyaltyProgramsService.updateClientPointsPerReservation(id, points);
            return ResponseEntity.ok(points);
        }
        catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/color")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<String> updateColor(
            @PathVariable(name = "id") UUID id,
            @RequestBody String color
    ) {
        try {
            loyaltyProgramsService.updateColor(id, color);
            return ResponseEntity.ok(color);
        }
        catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'MAIN_ADMIN')")
    public ResponseEntity<LoyaltyDto> addLoyaltyProgram(@RequestBody LoyaltyDto loyaltyDto) {
        try {
            return ResponseEntity.ok(loyaltyProgramsService.addLoyaltyProgram(loyaltyDto));
        }
        catch (LoyaltyAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
