package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.model.VacationHome;
import team10.app.model.VacationHomeOwner;
import team10.app.repository.VacationHomeOwnerRepository;
import team10.app.repository.VacationHomeRepository;
import team10.app.service.VacationHomeService;
import team10.app.util.exceptions.UserNotFoundException;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/vacation-homes")
@AllArgsConstructor
public class VacationHomeController {

//    private final VacationHomeService vacationHomeService;
    private final VacationHomeRepository vacationHomeRepository;
    private final VacationHomeOwnerRepository vacationHomeOwnerRepository;
    private final VacationHomeService vacationHomeService;


    @GetMapping(value = "/{id}")
    Set<VacationHome> getVacationHomesByUserId(@PathVariable("id") UUID id) {
        VacationHomeOwner vacationHomeOwner = vacationHomeOwnerRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
        return vacationHomeOwner.getVacationHomes();
    }

    @Transactional
    @PutMapping("/update/rooms/{id}/{rooms}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Integer> updateRooms(@PathVariable(name = "id") UUID id, @PathVariable(name = "rooms") int rooms) {
        try {
            vacationHomeService.updateRooms(rooms, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/beds/{id}/{beds}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Integer> updateBeds(@PathVariable(name = "id") UUID id, @PathVariable(name = "beds") int beds) {
        try {
            vacationHomeService.updateBeds(beds, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(beds, HttpStatus.OK);
    }

}
