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

    @Column(name = "loyalty_points", nullable = false)
    private int loyaltyPoints = 0;
    @Column(name="is_penalized", nullable = false)
    private Boolean isPenalized = false;
    @Column(name="penalty_points", nullable = false)
    private int penaltyPoints = 0;
    @OneToMany(mappedBy = "client",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    private Set<Rating> ratings;
  
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
