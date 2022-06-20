package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.RentalEntityRatingDto;
import team10.app.model.Rating;
import team10.app.repository.RatingRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RentalEntityService rentalEntityService;

    public List<RentalEntityRatingDto> getRatingReport(String businessClientEmail) {
        List<Object[]> ratings = ratingRepository.getAllByBusinessClientEmail(businessClientEmail);
        return ratings.stream().map(r -> new RentalEntityRatingDto((UUID) r[0], rentalEntityService.getTitle((UUID) r[0]), (Double) r[1])).collect(Collectors.toList());
    }

}
