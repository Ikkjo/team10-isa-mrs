package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ClientDto;
import team10.app.security.auth.JWTProvider;
import team10.app.service.ClientService;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final JWTProvider jwtProvider;

    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENT')")
    public ResponseEntity<ClientDto> getUserDetails(Principal principal) {
        try {
            return new ResponseEntity<>(clientService.getUserDetails(principal.getName()), HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
