package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.VacationHomeDTO;
import team10.app.service.VacationHomeOwnerService;

@RestController
@RequestMapping(path = "/api/v1/vacation-home-owner")
@AllArgsConstructor
public class VacationHomeOwnerController {

    private final VacationHomeOwnerService vacationHomeOwnerService;

    @PostMapping("/vacation-homes")
    public String addVacationHome(@RequestBody VacationHomeDTO request) {
        return vacationHomeOwnerService.addVacationHome(request);
    }

}
