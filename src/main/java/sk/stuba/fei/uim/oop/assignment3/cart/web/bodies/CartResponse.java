package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.payment.web.PaymentRequest;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponse {

    private final Long id;
    private final List<PaymentRequest> shoppingList;
    private final boolean payed;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.shoppingList = cart.getShoppingList().stream().map(p -> new PaymentRequest(p.getProduct().getId(), p.getAmount())).collect(Collectors.toList());
        this.payed=cart.isPayed();
    }

}
