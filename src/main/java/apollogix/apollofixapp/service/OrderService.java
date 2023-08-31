package apollogix.apollofixapp.service;

import apollogix.apollofixapp.model.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    Order createOrderFromProduct(List<Long> productIds);

    List<Order> getOrdersWithAtLeastProducts(int quantity);
}
