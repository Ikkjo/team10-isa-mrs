package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ShipDto;
import team10.app.security.auth.AuthUtil;
import team10.app.service.ShipOwnerService;
import team10.app.security.auth.JWTProvider;


@RestController
@RequestMapping(path = "/api/v1/ship-owner")
@AllArgsConstructor
public class ShipOwnerController {

    private final ShipOwnerService shipOwnerService;
    private final AuthUtil authUtil;

    @PostMapping("/add-ship")
    @PreAuthorize("hasRole('SHIP_OWNER')")
    public ResponseEntity<ShipDto> addShip(@RequestBody ShipDto request, @RequestHeader(name = "Authorization") String token) {
        try {
            shipOwnerService.addShip(request, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

}