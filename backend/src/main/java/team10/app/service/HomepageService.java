package team10.app.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team10.app.dto.RentalEntityDto;
import team10.app.model.RentalEntity;
import team10.app.repository.RentalEntityRepository;
import team10.app.repository.specification.search.SearchCriteria;

@Service
@AllArgsConstructor
public class HomepageService {

    RentalEntityService rentalEntityService;

    public List<RentalEntityDto> getDefaultHomepage() {
        int DEFAULT_PAGE_SIZE = 50;
        return rentalEntityService.getAllRentalEntitiesPage(0, DEFAULT_PAGE_SIZE);
    }

}
