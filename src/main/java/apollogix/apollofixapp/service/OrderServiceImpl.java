package apollogix.apollofixapp.service;

import apollogix.apollofixapp.model.Order;
import apollogix.apollofixapp.model.Product;
import apollogix.apollofixapp.repository.OrderRepository;
import apollogix.apollofixapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order createOrderFromProduct(List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);
        Order newOrder = new Order();
        newOrder.setProducts(products);
        orderRepository.save(newOrder);


        products.stream().forEach(product -> {
            product.setOrder(newOrder);
            productRepository.save(product);
        });
        return  newOrder;
    }

    @Override
    public List<Order> getOrdersWithAtLeastProducts(int quantity) {
        return orderRepository.getOrdersWithAtLeastProducts(quantity);
    }
}
