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

    private Integer penaltyPoints = 0;

    @OneToMany(mappedBy = "client",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "client",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    public Client() {

    }

    public Client(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
    }

    public Client(String firstName, String lastName, String email, String password, String phoneNumber) {
        super(firstName, lastName, email, password, UserRole.CLIENT, phoneNumber);

    }
}
