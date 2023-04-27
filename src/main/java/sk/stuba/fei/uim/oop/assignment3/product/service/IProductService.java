package sk.stuba.fei.uim.oop.assignment3.product.service;

import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdate;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product getProduct(Long id) throws NotFoundException;
    Product createProduct(ProductRequest body);
    Product updateProduct(Long id, ProductUpdate body) throws NotFoundException;
    void deleteProduct(Long id) throws NotFoundException;
    int getProductAmount(Long id) throws NotFoundException;
    int increaseProductAmount(Long id, int increment) throws NotFoundException;
}
