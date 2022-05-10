package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ShipDto;
import team10.app.service.ShipOwnerService;

@RestController
@RequestMapping(path = "/api/v1/ship-owner")
@AllArgsConstructor
public class ShipOwnerController {

    private final ShipOwnerService shipOwnerService;

    @PostMapping("/ships")
    public ResponseEntity<ShipDto> addShip(@RequestBody ShipDto request) {
        try {
            shipOwnerService.addShip(request);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

}