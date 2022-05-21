package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team10.app.dto.BusinessClientDto;
import team10.app.service.BusinessClientService;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/business-client")
@AllArgsConstructor
public class BusinessClientController {

    private final BusinessClientService businessClientService;

    @GetMapping
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<BusinessClientDto> getUserDetails(Principal principal) {
        try {
            return new ResponseEntity<>(businessClientService.getUserDetails(principal.getName()), HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
