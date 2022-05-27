package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.AddressDto;
import team10.app.dto.RentalEntityDto;
import team10.app.security.auth.JWTProvider;
import team10.app.service.RentalEntityService;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(path = "/api/v1/rental-entity")
@AllArgsConstructor
public class RentalEntityController {

    private final RentalEntityService rentalEntityService;
    private final JWTProvider jwtProvider;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<RentalEntityDto> findById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(rentalEntityService.rentalEntityToDto(id), HttpStatus.OK);
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

    @Transactional
    @PutMapping("/update/title/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateTitle(@PathVariable UUID id, @RequestBody String title) {
        try {
            rentalEntityService.updateTitle(title, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/address/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable UUID id, @RequestBody AddressDto address) {
        try {
            rentalEntityService.updateAddress(address, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/description/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateDescription(@PathVariable UUID id, @RequestBody String description) {
        try {
            rentalEntityService.updateDescription(description, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(description, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/rules-of-conduct/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateRulesOfConduct(@PathVariable UUID id, @RequestBody String rulesOfConduct) {
        try {
            rentalEntityService.updateRulesOfConduct(rulesOfConduct, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rulesOfConduct, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/additional-services/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<String> updateAdditionalServices(@PathVariable UUID id, @RequestBody String additionalServices) {
        try {
            rentalEntityService.updateAdditionalServices(additionalServices, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(additionalServices, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/update/price/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<Integer> updatePrice(@PathVariable UUID id, @RequestBody String price) {
        try {
            rentalEntityService.updatePrice(price, id);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Integer.parseInt(price), HttpStatus.OK);
    }



}
