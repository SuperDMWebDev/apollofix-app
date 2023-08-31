package apollogix.apollofixapp.repository;


import apollogix.apollofixapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select o from Order o where size(o.products) >= :quantity")
    List<Order> getOrdersWithAtLeastProducts(@Param("quantity") int quantity);
}
