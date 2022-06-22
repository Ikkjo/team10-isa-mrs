package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team10.app.model.ClientReport;

import java.util.UUID;

@Repository
public interface ClientReportRepository extends JpaRepository<ClientReport, UUID> {
}
