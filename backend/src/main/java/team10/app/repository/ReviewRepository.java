package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Client;
import team10.app.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findReviewById(Long id);


    Page<Review> findAll(Pageable pageable);

    Review findReviewByClient(Client client);
}
