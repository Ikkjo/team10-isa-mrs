package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.VacationHomeDto;
import team10.app.security.auth.AuthUtil;
import team10.app.security.auth.JWTProvider;
import team10.app.service.VacationHomeOwnerService;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/vacation-home-owner")
@AllArgsConstructor
public class VacationHomeOwnerController {

    private final VacationHomeOwnerService vacationHomeOwnerService;
    private final AuthUtil authUtil;

    @PostMapping("/add-vacation-home")
    @PreAuthorize("hasRole('HOUSE_OWNER')")
    public ResponseEntity<VacationHomeDto> addVacationHome(@RequestBody VacationHomeDto request, @RequestHeader(name = "Authorization") String token) {
        try {
             vacationHomeOwnerService.addVacationHome(request, authUtil.getEmailFromToken(token));
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

}
