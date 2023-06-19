package ua.store.domain.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.store.domain.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /*@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND (lower( p.name ) LIKE '%' || lower(:search) ||'%' OR lower(p.description) LIKE '%' || lower(:search) )")
    List<Product> findAll(Pageable pageable, int categoryId, String search);*/

    /*     @Query("SELECT p FROM Product p WHERE  (((:search IS NULL) OR lower(p.name) LIKE  % lower(:search) %) AND ((:categoryId IS NULL) OR p.category.id = :categoryId))")
         List<Product> findAll(Pageable pageable, @Param("search") String search, @Param("categoryId") Integer categoryId);
   */

    @Query("select p from Product p " +
            "where ((lower(p.name) like concat('%', lower(:search) ,'%') or :search is null) " +
            "or (lower(p.description) like concat('%', lower(:search) ,'%') or :search is null)) " +
            "and ( p.category.id = :categoryId or :categoryId is null ) order by p.name")
    List<Product> findAll(Pageable pageable, @Param("search") String search, @Param("categoryId") Integer categoryId);

}
