package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.RentalEntityDto;
import team10.app.service.RentalEntityService;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(path = "/api/v1/rental-entity")
@AllArgsConstructor
public class RentalEntityController {

    private final RentalEntityService rentalEntityService;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<RentalEntityDto> findById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(rentalEntityService.findById(id), HttpStatus.OK);
        }
        catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Set<RentalEntityDto>> getAllVacationHomes(Principal principal) {
        try {
            return new ResponseEntity<>(rentalEntityService.getAllActiveByOwnerEmail(principal.getName()), HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
