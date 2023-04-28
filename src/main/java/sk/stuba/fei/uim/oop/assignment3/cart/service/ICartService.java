package sk.stuba.fei.uim.oop.assignment3.cart.service;

import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.web.CartRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

public interface ICartService {
    Cart createCart();

    Cart getCart(Long id) throws NotFoundException;

    void deleteCart(Long id) throws NotFoundException;

    Cart addToCart(Long id, CartRequest body) throws NotFoundException, IllegalOperationException;

    double payForCart(Long id) throws NotFoundException, IllegalOperationException;
}
