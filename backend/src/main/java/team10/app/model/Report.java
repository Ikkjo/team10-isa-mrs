package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.dto.ReportDto;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String message;
    private boolean penalize;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    private RentalEntity rentalEntity;
    private boolean reviewed;

    @Version
    private Long version;

    public Report(ReportDto reportDto) {
        this.message = reportDto.getMessage();
        this.penalize = reportDto.isPenalize();
        if (this.penalize){
            this.reviewed = false;
        }
    }

    // TODO: add rating collection, update model diagram to support ReviewStatus
}
