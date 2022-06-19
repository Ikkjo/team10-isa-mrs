package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.Report;
import team10.app.model.UserRole;

import java.util.UUID;

@Getter
@Setter
public class AdminReportDto {
    private UUID id;
    private UserRole reportedByRole;
    private String reportedBy;
    private String personReported;
    private String rentalEntityTitle;
    private String message;

    public AdminReportDto(Report report) {
        this.id = report.getId();
        this.reportedByRole = report.getRentalEntity().getOwner().getRole();
        this.reportedBy = report.getRentalEntity().getOwner().getEmail();
        this.personReported = report.getClient().getEmail();
        this.rentalEntityTitle = report.getRentalEntity().getTitle();
        this.message = report.getMessage();
    }
}
