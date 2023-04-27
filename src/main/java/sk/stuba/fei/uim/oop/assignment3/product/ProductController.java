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

    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable("id") Long id, @RequestBody Product body) throws NotFoundException {
        Product product = this.repository.findProductById(id);
        if (product != null) {
            if (body.getName() != null) {
                product.setName(body.getName());
            }
            if (body.getDescription() != null) {
                product.setDescription(body.getDescription());
            }
            this.repository.save(product);
            return product;
        }
        throw new NotFoundException();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        Product product = this.repository.findProductById(id);
        if (product != null) {
            this.repository.delete(product);
            return;
        }
        throw new NotFoundException();
    }

    @GetMapping("/{id}/amount")
    public ProductAmount getProductAmount(@PathVariable("id") Long id) throws NotFoundException {
        Product product = getProductById(id);
        if (product != null) {
            return new ProductAmount(product.getAmount());
        }
        throw new NotFoundException();
    }

    @PostMapping("/{id}/amount")
    public ProductAmount increaseProductAmount(@PathVariable("id") Long id, @RequestBody Product body) throws NotFoundException {
        Product product = getProductById(id);
        if (product != null) {
            product.setAmount(product.getAmount()+body.getAmount());
            this.repository.save(product);
            return new ProductAmount(product.getAmount());
        }
        throw new NotFoundException();
    }

}
