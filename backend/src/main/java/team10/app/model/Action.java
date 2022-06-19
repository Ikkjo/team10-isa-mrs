package team10.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.dto.ActionDto;
import team10.app.util.DateTimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Action extends Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int maxPersons;
    private long expiresOn;

    public Action(ActionDto actionDto) {
        super(actionDto.getDateRange().get(0), actionDto.getDateRange().get(1), actionDto.getPrice());
        this.maxPersons = actionDto.getMaxPersons();
        this.expiresOn = actionDto.getExpiresOn();
    }

    public boolean isExpired() {
        return this.expiresOn < DateTimeUtil.getTodayEpochMillisecond();
    }

}
