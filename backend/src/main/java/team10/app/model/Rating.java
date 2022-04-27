package team10.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rating {

    @Id
    private Long id;
    private Double userRating;
    private Double serviceRating;

    @OneToOne(fetch = FetchType.EAGER)
    private Review review;

    public Rating(){

    }
}
