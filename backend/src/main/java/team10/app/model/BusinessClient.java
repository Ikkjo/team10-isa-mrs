package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="business_client")
public abstract class BusinessClient extends User {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(nullable = false)
    private String dateOfBirth;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loyalty_id")
    private Loyalty loyalty;
    @OneToMany(mappedBy = "businessClient")
    protected Set<Reservation> reservations;


    protected BusinessClient(String firstName, String lastName, String email, String password, UserRole appUserRole, String phoneNumber, Address address, String dateOfBirth) {
        super(firstName, lastName, email, password, appUserRole, phoneNumber);
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.loyalty = new Loyalty();
    }

    protected BusinessClient(String firstName, String lastName, String email, String password, UserRole appUserRole) {
        super(firstName, lastName, email, password, appUserRole);
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
