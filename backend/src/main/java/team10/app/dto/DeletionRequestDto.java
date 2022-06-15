package team10.app.dto;

import lombok.Getter;
import lombok.Setter;
import team10.app.model.DeletionRequest;
import team10.app.model.UserRole;

@Getter
@Setter
public class DeletionRequestDto {
    private String id;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String deletionReason;

    public DeletionRequestDto(DeletionRequest dr){
        this.id = dr.getId().toString();
        this.firstName = dr.getUser().getFirstName();
        this.lastName = dr.getUser().getLastName();
        this.role = dr.getUser().getRole();
        this.deletionReason = dr.getDeletionReason();
    }
}
