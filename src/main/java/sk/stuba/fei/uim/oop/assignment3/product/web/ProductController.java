package sk.stuba.fei.uim.oop.assignment3.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.service.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductAmount;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductResponse;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long id) throws NotFoundException {
        return new ProductResponse(this.service.getProduct(id));
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest body) {
        return new ResponseEntity<>(new ProductResponse(this.service.createProduct(body)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProductById(@PathVariable("id") Long id, @RequestBody ProductUpdate body) throws NotFoundException {
        return new ProductResponse(this.service.updateProduct(id, body));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        this.service.deleteProduct(id);
    }

    @GetMapping("/{id}/amount")
    public ProductAmount getProductAmount(@PathVariable("id") Long id) throws NotFoundException {
        return new ProductAmount(this.service.getProductAmount(id));
    }

    @PostMapping("/{id}/amount")
    public ProductAmount increaseProductAmount(@PathVariable("id") Long id, @RequestBody ProductAmount body) throws NotFoundException {
        return new ProductAmount(this.service.changeProductAmount(id, body.getAmount()));
    }

}
