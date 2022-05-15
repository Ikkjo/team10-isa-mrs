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
    @JoinTable(name = "business_partner_addresses", joinColumns = @JoinColumn(name = "business_partner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    private Address address;
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
