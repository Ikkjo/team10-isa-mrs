package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team10.app.dto.AdventureDto;
import team10.app.service.FishingInstructorService;

@RestController
@RequestMapping(path = "/api/v1/fishing-instructor")
@AllArgsConstructor
public class FishingInstructorController {

    private final FishingInstructorService fishingInstructorService;

//    @PostMapping("/add-adventure")
//    public ResponseEntity<AdventureDto> addAdventure(@RequestBody AdventureDto request) {
//        try {
//            fishingInstructorService.addAdventure(request);
//        }
//        catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(request, HttpStatus.CREATED);
//    }

}
