package apollogix.apollofixapp.service;

import apollogix.apollofixapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
     List<Product> getAllProducts();

     Page<Product> getProducts(int page, int size);

     Product getProductById(Long id);

     Product createProduct(Product product);

     Page<Product> findByNameContainingIgnoreCase(String keyword,  int page, int size);


}
