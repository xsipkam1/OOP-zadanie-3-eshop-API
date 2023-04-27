package sk.stuba.fei.uim.oop.assignment3.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdate;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product getProduct(Long id) throws NotFoundException {
        Product product = this.repository.findProductById(id);
        if (product != null) {
            return product;
        }
        throw new NotFoundException();
    }

    @Override
    public Product createProduct(ProductRequest body) {
        Product product = new Product(body.getName(), body.getDescription(), body.getAmount(), body.getUnit(), body.getPrice());
        this.repository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, ProductUpdate body) throws NotFoundException{
        Product product = getProduct(id);
        if (body.getName() != null) {
            product.setName(body.getName());
        }
        if (body.getDescription() != null) {
            product.setDescription(body.getDescription());
        }
        this.repository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) throws NotFoundException {
        Product product = getProduct(id);
        this.repository.delete(product);
    }

    @Override
    public int getProductAmount(Long id) throws NotFoundException {
        Product product = getProduct(id);
        return product.getAmount();
    }

    @Override
    public int increaseProductAmount(Long id, int increment) throws NotFoundException {
        Product product = getProduct(id);
        product.setAmount(product.getAmount()+increment);
        this.repository.save(product);
        return product.getAmount();
    }

}
