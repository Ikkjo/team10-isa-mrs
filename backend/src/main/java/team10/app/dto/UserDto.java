package team10.app.dto;

import lombok.*;
import team10.app.model.User;
import team10.app.model.UserRole;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    protected UUID id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected UserRole role;
    protected boolean deleted;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
        this.deleted = user.getDeleted();
    }
}
