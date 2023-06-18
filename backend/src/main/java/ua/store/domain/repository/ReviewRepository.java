package ua.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.store.domain.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
