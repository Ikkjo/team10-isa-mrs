package team10.app.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class DeletionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private String deletionReason;
    private boolean reviewed = false;

    @Version
    private Long version;

    public DeletionRequest(User user, String deletionReason){
        this.user = user;
        this.deletionReason = deletionReason;
    }
}