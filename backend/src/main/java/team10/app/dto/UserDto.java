package team10.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class UserDto {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String role;

}
