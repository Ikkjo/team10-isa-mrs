package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team10.app.dto.ReviewDto;
import team10.app.security.auth.JWTProvider;
import team10.app.service.ReviewService;
import team10.app.util.exceptions.ReviewInvalidException;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final JWTProvider jwtProvider;

    @Transactional
    @PutMapping("/{id}/didnt-arrive")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR')")
    public ResponseEntity<HttpStatus> addClientDidntArrive(Principal principal, @PathVariable UUID id) {
        try {
            reviewService.addClientDidntArrive(principal.getName(), id);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOUSE_OWNER', 'SHIP_OWNER', 'FISHING_INSTRUCTOR', 'CLIENT')")
    public ResponseEntity<ReviewDto> addReview(@RequestHeader (name="Authorization") String token, @PathVariable UUID id, @RequestBody ReviewDto reviewDto) {
        try {
            return new ResponseEntity<>(reviewService.addReview(
                    jwtProvider.getAuthentication(token.substring(7)).getName(), id, reviewDto), HttpStatus.OK);
        }
        catch (ReviewInvalidException | UsernameNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
