package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.VacationHomeDto;
import team10.app.service.VacationHomeOwnerService;

@RestController
@RequestMapping(path = "/api/v1/vacation-home-owner")
@AllArgsConstructor
public class VacationHomeOwnerController {

    private final VacationHomeOwnerService vacationHomeOwnerService;

    @CrossOrigin(origins = "*")
    @PostMapping("/vacation-homes")
    public ResponseEntity<VacationHomeDto> addVacationHome(@RequestBody VacationHomeDto request) {
        try {
             Boolean success = vacationHomeOwnerService.addVacationHome(request);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

}
