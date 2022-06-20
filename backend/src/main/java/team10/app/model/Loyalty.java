package team10.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "loyalty")
@Table(name = "loyalty")
@NoArgsConstructor
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private int availableAtPoints;
    private double clientDiscount;
    private double businessClientCut;
    private int clientPointsPerReservation;
    private int businessClientPointsPerReservation;
    private String color;
    private boolean deletable = true;

}
