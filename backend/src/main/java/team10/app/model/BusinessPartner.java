package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
public class BusinessPartner extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address; // TODO: Skontati kako cuvati adresu u bazi
    private Loyalty loyalty;

    public BusinessPartner(String firstName, String lastName, String email, String password, UserRole appUserRole, String phoneNumber, Address address) {
        super(firstName, lastName, email, password, appUserRole, phoneNumber);
        this.loyalty = new Loyalty();
        this.address = address;
    }

    public BusinessPartner(String firstName, String lastName, String email, String password, UserRole appUserRole) {
        super(firstName, lastName, email, password, appUserRole);
    }
}
