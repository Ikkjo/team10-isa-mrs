package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.Rating;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    @Query("select r.rentalEntity.id, avg(r.rating)from Rating r where r.rentalEntity.owner.email = ?1 group by r.rentalEntity.id")
    List<Object[]> getAllByBusinessClientEmail(String businessClientEmail);

}
