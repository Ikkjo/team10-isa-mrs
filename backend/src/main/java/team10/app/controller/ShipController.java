package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.service.ShipService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/ship")
@AllArgsConstructor
public class ShipController {

    private final ShipService shipService;

    @Transactional
    @PutMapping("/update/type/{id}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<String> updateType(@PathVariable UUID id, @RequestBody String type) {
        try {
            shipService.updateType(type, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/length/{id}/{length}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<Double> updateLength(@PathVariable(name = "id") UUID id, @PathVariable(name = "length") double length) {
        try {
            shipService.updateLength(length, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(length, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/engine-count/{id}/{engine-count}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<Integer> updateEngineCount(@PathVariable(name = "id") UUID id, @PathVariable(name = "engine-count") int engineCount) {
        try {
            shipService.updateEngineCount(engineCount, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(engineCount, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/engine-power/{id}/{engine-power}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<Integer> updateEnginePower(@PathVariable(name = "id") UUID id, @PathVariable(name = "engine-power") int enginePower) {
        try {
            shipService.updateEnginePower(enginePower, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(enginePower, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/max-speed/{id}/{max-speed}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<Integer> updateMaxSpeed(@PathVariable(name = "id") UUID id, @PathVariable(name = "max-speed") int maxSpeed) {
        try {
            shipService.updateMaxSpeed(maxSpeed, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(maxSpeed, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/navigation-equipment/{id}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<String> updateNavigationEquipment(@PathVariable UUID id, @RequestBody String navigationEquipment) {
        try {
            shipService.updateNavigationEquipment(navigationEquipment, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(navigationEquipment, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/fishing-equipment/{id}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<String> updateFishingEquipment(@PathVariable UUID id, @RequestBody String fishingEquipment) {
        try {
            shipService.updateFishingEquipment(fishingEquipment, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fishingEquipment, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/capacity/{id}/{capacity}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<Integer> updateCapacity(@PathVariable(name = "id") UUID id, @PathVariable(name = "capacity") int capacity) {
        try {
            shipService.updateCapacity(capacity, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(capacity, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/free-cancellation/{id}")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<Boolean> updateFreeCancellation(@PathVariable UUID id, @RequestBody Boolean isFreeCancellation) {
        try {
            shipService.updateFreeCancellation(isFreeCancellation, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(isFreeCancellation, HttpStatus.OK);
    }

}
