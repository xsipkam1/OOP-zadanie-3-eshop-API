package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartRequest {
    private Long productId;
    private int amount;
}
