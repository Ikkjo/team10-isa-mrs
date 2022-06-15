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

@Service
@AllArgsConstructor
public class HomepageService {

    RentalEntityRepository rentalEntityRepository;
    RentalEntityService rentalEntityService;

    public List<RentalEntityDto> getAllRentalEntitiesPage(int page, int size) {
        List<RentalEntity> rentalEntityPage = rentalEntityRepository.findAll(PageRequest.of(page, size)).toList();
        List<RentalEntityDto> rentalEntityDtoList = new ArrayList<>();

        for (RentalEntity re : rentalEntityPage) {
            rentalEntityDtoList.add(rentalEntityService.rentalEntityToDto(re.getId()));
        }
        return rentalEntityDtoList;
    }

}
