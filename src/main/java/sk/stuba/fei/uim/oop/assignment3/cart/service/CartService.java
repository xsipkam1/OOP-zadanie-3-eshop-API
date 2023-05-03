package sk.stuba.fei.uim.oop.assignment3.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.data.ICartRepository;
import sk.stuba.fei.uim.oop.assignment3.cart.web.bodies.CartRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.payment.IPaymentRepository;
import sk.stuba.fei.uim.oop.assignment3.payment.Payment;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.service.IProductService;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository repository;

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private IProductService productService;

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        this.repository.save(cart);
        return cart;
    }

    @Override
    public Cart getCart(Long id) throws NotFoundException {
        Cart cart = this.repository.findCartById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        return cart;
    }

    @Override
    public void deleteCart(Long id) throws NotFoundException {
        Cart cart = getCart(id);
        this.repository.delete(cart);
    }

    @Override
    public Cart addToCart(Long id, CartRequest body) throws NotFoundException, IllegalOperationException {
        Cart cart = getCart(id);
        if (cart.isPayed()) {
            throw new IllegalOperationException();
        }
        Product product = productService.getProduct(body.getProductId());
        if (product.getAmount() < body.getAmount()) {
            throw new IllegalOperationException();
        }

        Payment payment = new Payment(cart, product, body.getAmount(), body.getAmount() * product.getPrice());
        payment = this.paymentRepository.save(payment);
        product.getPayments().add(payment);

        boolean hasProduct = false;
        for (Payment p : cart.getShoppingList()) {
            if (p.getProduct().getId().equals(payment.getProduct().getId())) {
                p.setAmount(p.getAmount() + body.getAmount());
                p.setPrice(product.getPrice() * p.getAmount());
                hasProduct = true;
                break;
            }
        }

        if (!hasProduct) {
            cart.getShoppingList().add(payment);
        }

        this.productService.increaseProductAmount(body.getProductId(), -body.getAmount());
        this.repository.save(cart);
        return cart;
    }

    @Override
    public double payForCart(Long id) throws NotFoundException, IllegalOperationException {
        Cart cart = getCart(id);
        if (cart.isPayed()) {
            throw new IllegalOperationException();
        }
        double sum = 0;
        for (Payment p : cart.getShoppingList()) {
            sum += p.getPrice();
        }
        cart.setPayed(true);
        this.repository.save(cart);
        return sum;
    }

}
