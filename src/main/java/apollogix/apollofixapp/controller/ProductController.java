package apollogix.apollofixapp.controller;

import apollogix.apollofixapp.model.Product;
import apollogix.apollofixapp.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path="/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);

        if(product !=null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product newProduct = productService.createProduct(product);

        if(newProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status","success"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path="/filter")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String keyword
    ) {
        if(keyword != ""){
            return ResponseEntity.ok(productService.findByNameContainingIgnoreCase(keyword, page,size));
        }
        return ResponseEntity.ok(productService.getProducts(page,size));
    }

}