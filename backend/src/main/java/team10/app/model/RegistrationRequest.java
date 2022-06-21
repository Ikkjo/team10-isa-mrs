package team10.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_client_id")
    private BusinessClient businessClient;
    @Column(nullable = false)
    private String description;
    private boolean reviewed = false;

    @Version
    private Long version;

    public RegistrationRequest(BusinessClient businessClient, String description){
        this.businessClient = businessClient;
        this.description = description;
    }
}
