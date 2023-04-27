package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductRepository repository;

    @GetMapping()
    public List<Product> getAllProducts() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws NotFoundException {
        Product product = this.repository.findProductById(id);
        if (product != null) {
            return product;
        }
        throw new NotFoundException();
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product body) {
        Product product = new Product(body.getName(), body.getDescription(), body.getAmount(), body.getUnit(), body.getPrice());
        this.repository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
