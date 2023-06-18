package ua.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.store.domain.model.Order;
import ua.store.domain.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByOwner(User owner);

    @Modifying
    @Query("update Order o set o.status.id = :statusId where o.id = :orderId")
    void updateStatus(@Param("orderId") int orderId, @Param("statusId") int statusId);
}
