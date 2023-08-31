package apollogix.apollofixapp.controller;

import apollogix.apollofixapp.model.Order;
import apollogix.apollofixapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody List<Long> productIds) {
        Order newOrder = orderService.createOrderFromProduct(productIds);

        if (newOrder != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status", "success"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    };

    @GetMapping("/at-least-products")
    public ResponseEntity<List<Order>> getOrdersWithAtLeastProducts(@RequestParam(defaultValue = "0") int quantity) {
        List<Order> orders = orderService.getOrdersWithAtLeastProducts(quantity);

        if(!orders.isEmpty()) {
            return new ResponseEntity<>(orders,HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}


