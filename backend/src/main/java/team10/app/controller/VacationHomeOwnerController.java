package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.VacationHomeDto;
import team10.app.service.VacationHomeOwnerService;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/vacation-home-owner")
@AllArgsConstructor
public class VacationHomeOwnerController {

    private final VacationHomeOwnerService vacationHomeOwnerService;

    @CrossOrigin(origins = "*")
    @PostMapping("/vacation-homes")
    public String addVacationHome(@RequestBody VacationHomeDto request) {
        try {
            return vacationHomeOwnerService.addVacationHome(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
