package team10.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;
    @Column(nullable = false)
    protected String title;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    protected Address address;
    @Column(nullable = false)
    protected String description;
    @Column(nullable = false)
    protected String rulesOfConduct;
    @Column(nullable = false)
    protected String additionalServices;
    @Column(nullable = false)
    protected int price;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    protected Set<Picture> pictures;
    protected boolean deleted = false;

}
