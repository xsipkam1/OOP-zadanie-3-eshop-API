package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;

import lombok.Data;

@Data
public class CartRequest {
    private Long productId;
    private int amount;
}
