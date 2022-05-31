package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.AdminDto;
import team10.app.security.auth.JWTProvider;
import team10.app.service.AdminService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final JWTProvider jwtProvider;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDto> getUserDetails(Principal principal) {
        try {
            return new ResponseEntity<>(adminService.getUserDetails(principal.getName()), HttpStatus.OK);
        }
        catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping(path = "/registration-request/{id}/accept")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> acceptBusinessClient(@PathVariable UUID id) {
        try {
            adminService.acceptBusinessClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @PutMapping(path = "/registration-request/{id}/decline")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> denyBusinessClient(@PathVariable UUID id, @RequestBody String declineReason) {
        try {
            adminService.declineBusinessClient(id, declineReason);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
