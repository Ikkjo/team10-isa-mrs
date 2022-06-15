package team10.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return ResponseEntity.ok(homepageService.getAllRentalEntitiesPage(0, 20));
    }

    @PostMapping
    public ResponseEntity<List<RentalEntityDto>> homepageSearch(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(name = "title") String title
    ) {
        return ResponseEntity.ok(null);
    }
}
