package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Review {

    @Id
    private Long id;
    private String review;
    private ReviewStatus status;
    private LocalDate createdAt;
    private LocalDate approvedAt;
    private LocalDate answeredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Review() {
        this.status = ReviewStatus.CREATED;
        createdAt = LocalDate.now();
    }

    // TODO: add rating collection, update model diagram to support ReviewStatus
}
