package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Action extends Reservation{

    @Id
    private Long id;
    private Integer maxPersons;
    private LocalDate duration;

    public Action() {

    }


}
