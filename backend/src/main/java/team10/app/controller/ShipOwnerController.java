package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ShipDto;
import team10.app.dto.VacationHomeDto;
import team10.app.security.auth.JWTProvider;
import team10.app.service.ShipOwnerService;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/ship-owner")
@AllArgsConstructor
public class ShipOwnerController {

    private final ShipOwnerService shipOwnerService;
    private final JWTProvider jwtProvider;

    @PostMapping("/add-ship")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<ShipDto> addShip(@RequestBody ShipDto request, @RequestHeader(name = "Authorization") String token) {
        try {
            shipOwnerService.addShip(request, jwtProvider.getAuthentication(token.substring(7)).getName());
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

}