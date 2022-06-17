package team10.app.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.RentalEntityDto;
@Service
@AllArgsConstructor
public class HomepageService {

    RentalEntityService rentalEntityService;

    public List<RentalEntityDto> getDefaultHomepage() {
        int DEFAULT_PAGE_SIZE = 20;
        return rentalEntityService.getAllRentalEntitiesPage(0, DEFAULT_PAGE_SIZE);
    }

}
