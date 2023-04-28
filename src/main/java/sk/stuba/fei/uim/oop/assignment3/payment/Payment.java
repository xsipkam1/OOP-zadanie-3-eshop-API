package sk.stuba.fei.uim.oop.assignment3.payment;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    private int amount;
    private double price;

    public Payment(Cart cart, Product product, int amount, double price) {
        this.cart = cart;
        this.product = product;
        this.amount = amount;
        this.price=price;
    }

}
