package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Client")
@Table(name = "client")
public class Client extends User{

    //TODO: add @Column for all fields, create client table

/*    @OneToOne(mappedBy = "client",
              fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    private Loyalty loyalty;*/

    private Boolean isPenalized = false;

    private int penaltyPoints = 0;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private Set<Reservation> reservations;

    public Client() {

    }

    public Client(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
    }

    public Client(String firstName, String lastName, String email, String password, String phoneNumber) {
        super(firstName, lastName, email, password, UserRole.CLIENT, phoneNumber);

    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
