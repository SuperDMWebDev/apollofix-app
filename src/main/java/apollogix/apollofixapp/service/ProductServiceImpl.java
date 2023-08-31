package apollogix.apollofixapp.service;

import apollogix.apollofixapp.model.Product;
import apollogix.apollofixapp.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    @Override
    public List<Product> getAllProducts() {
        logger.info("get all products");
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findByNameContainingIgnoreCase(String keyword,  int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }

}
