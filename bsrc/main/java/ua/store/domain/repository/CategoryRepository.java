package ua.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.store.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
