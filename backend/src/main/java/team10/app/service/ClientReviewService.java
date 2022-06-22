package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.dto.ClientReviewDto;
import team10.app.model.ClientReview;
import team10.app.repository.ClientReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientReviewService {

    private final ClientReviewRepository clientReviewRepository;

    public List<ClientReview> getClientReviews() {
        return clientReviewRepository.findAll();
    }

    public List<ClientReviewDto> getClientReviewDtoList(List<ClientReview> clientReviews) {
        return clientReviews.stream()
                .map(ClientReviewDto::new)
                .collect(Collectors.toList());
    }
}
