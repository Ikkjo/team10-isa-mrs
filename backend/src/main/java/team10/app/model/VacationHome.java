package team10.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team10.app.dto.VacationHomeDTO;
import team10.app.model.Room;
import team10.app.model.RulesOfConduct;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="vacation_homes")
public class VacationHome {

    @SequenceGenerator(name = "vacation_home_sequence", sequenceName = "vacation_home_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacation_home_sequence")
    private Long id;
    @Column(nullable = false)
    private String title;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vacation_home_rooms", joinColumns = @JoinColumn(name = "vacation_home_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"))
    private List<Room> rooms;
    @Column(nullable = false)
    private int price;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "vacation_home_rulesOfConduct", joinColumns = @JoinColumn(name = "vacation_home_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rules_of_conduct_id", referencedColumnName = "id"))
    private RulesOfConduct rulesOfConduct;
    @Column(nullable = false)
    private String additionalServices;

    public VacationHome(VacationHomeDTO vacationHomeDTO) {
        this.title = vacationHomeDTO.getTitle();
        this.address = vacationHomeDTO.getAddress();
        this.rooms = vacationHomeDTO.getRooms();
        this.price = vacationHomeDTO.getPrice();
        this.rulesOfConduct = vacationHomeDTO.getRulesOfConduct();
        this.additionalServices = vacationHomeDTO.getAdditionalServices();
    }


}