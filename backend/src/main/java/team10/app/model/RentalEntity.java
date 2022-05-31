package team10.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    protected String title;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    protected Address address;
    @Column(nullable = false, length = 500)
    protected String description;
    @Column(nullable = false, length = 200)
    protected String rulesOfConduct;
    @Column(nullable = false, length = 200)
    protected String additionalServices;
    @Column(nullable = false)
    protected int price;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "picture_id")
    protected Set<Picture> pictures;
    protected boolean deleted = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    protected BusinessClient owner;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_id")
    protected Set<Availability> availability;

    public void setPictures(Set<Picture> pictures) {
        this.pictures.clear();
        if (pictures != null) {
            this.pictures.addAll(pictures);
        }
    }
}
