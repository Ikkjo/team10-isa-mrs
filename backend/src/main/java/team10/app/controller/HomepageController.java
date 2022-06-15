package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team10.app.dto.RentalEntityDto;
import team10.app.model.RentalEntity;
import team10.app.service.HomepageService;

@Controller
@AllArgsConstructor
@RequestMapping(path = "api/v1/homepage")
public class HomepageController {

    HomepageService homepageService;

    @GetMapping
    public ResponseEntity<List<RentalEntityDto>> getDefaultHomepage() {
        return ResponseEntity.ok(homepageService.getAllRentalEntitiesPage(1, 20));
    }

    @PostMapping
    public ResponseEntity<List<RentalEntityDto>> homepageSearch() {
        return ResponseEntity.ok(null);
    }
}
