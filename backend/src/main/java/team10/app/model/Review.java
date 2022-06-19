package team10.app.model;

import lombok.Getter;
import lombok.Setter;
import team10.app.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String message;
    private ReviewStatus status;
    private long createdAt;
    private long approvedAt;
    private long answeredAt;
    private boolean penalize;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @OneToOne
    private BusinessClient businessClient;


    public Review() {
        this.status = ReviewStatus.CREATED;
        createdAt = DateTimeUtil.getTodayEpochMillisecond();
    }

    public Review(String message, boolean penalize) {
        this.message = message;
        this.createdAt = DateTimeUtil.getTodayEpochMillisecond();
        this.status = ReviewStatus.CREATED;
        this.penalize = penalize;
    }

    // TODO: add rating collection, update model diagram to support ReviewStatus
}
