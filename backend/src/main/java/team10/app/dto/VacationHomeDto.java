package team10.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import team10.app.model.Address;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationHomeDto {
    private String title;
    private Address address;
    private String description;
    private String rulesOfConduct;
    private String additionalServices;
    private int price;
    private int rooms;
    private int beds;
}
