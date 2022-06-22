package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.ClientReview;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ClientReviewDto {
    private UUID id;
    private String clientEmail;
    private String rentalEntityTitle;
    private int rating;
    private String message;

    public ClientReviewDto(ClientReview clientReview) {
        // TODO: When Ilija implements ClientReview
    }
}
