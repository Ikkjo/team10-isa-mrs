package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team10.app.model.Client;
import team10.app.model.DeletionRequest;
import team10.app.model.Report;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {

    Page<Report> findAll(Pageable pageable);

    @Query(
            value = "select r from Report r where r.reviewed = false and r.penalize = true",
            countQuery = "select count(r) from Report r where r.reviewed = false and r.penalize = true"
    )
    Page<Report> findAllNotReviewed(Pageable pageable);
}
