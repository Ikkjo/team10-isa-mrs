package team10.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    protected Address address;
    @Column(nullable = false)
    protected String title;
    @Column(nullable = false)
    protected String description;
    @ElementCollection
    @CollectionTable(name = "my_pictures", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "pictures", nullable = false)
    protected List<String> pictures;
    @Column(nullable = false)
    protected String rulesOfConduct;
    @Column(nullable = false)
    protected String additionalServices;
    @Column(nullable = false)
    protected int price;
    protected boolean deleted = false;

}
