package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.service.AdventureService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/adventure")
@AllArgsConstructor
public class AdventureController {
    private final AdventureService adventureService;

    @Transactional
    @PutMapping("/update/{id}/capacity/{capacity}")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<HttpStatus> updateCapacity(@PathVariable(name = "id") UUID id, @PathVariable(name = "capacity") int capacity) {
        try {
            adventureService.updateCapacity(capacity, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/free-cancellation/{is-free-cancellation}")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<HttpStatus> updateFreeCancellation(@PathVariable(name = "id") UUID id, @PathVariable(name = "is-free-cancellation") boolean isFreeCancellation) {
        try {
            adventureService.updateFreeCancellation(isFreeCancellation, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/fishing-equipment")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<HttpStatus> updateFishingEquipment(@PathVariable UUID id, @RequestBody String fishingEquipment) {
        try {
            adventureService.updateFishingEquipment(fishingEquipment, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping("/update/{id}/biography")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<HttpStatus> updateBiography(@PathVariable UUID id, @RequestBody String biography) {
        try {
            adventureService.updateBiography(biography, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
