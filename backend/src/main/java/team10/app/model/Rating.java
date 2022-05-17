package team10.app.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Double userRating;
    private Double serviceRating;

    @OneToOne(fetch = FetchType.EAGER)
    private Review review;


    public Rating(){

    }
}
