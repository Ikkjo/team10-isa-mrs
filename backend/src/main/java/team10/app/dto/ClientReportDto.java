package team10.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.model.ClientReport;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ClientReportDto {
    private UUID id;
    private String clientEmail;
    private String rentalEntityTitle;
    private String businessClientEmail;
    private String complaint;

    public ClientReportDto(ClientReport clientReport) {
        // TODO: When Ilija implements ClientReview
    }
}
