package sk.stuba.fei.uim.oop.assignment3.payment.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.payment.data.Payment;

@Getter
public class PaymentResponse {
    private final Long productId;
    private final int amount;

    public PaymentResponse(Payment p) {
        this.productId = p.getProduct().getId();
        this.amount = p.getAmount();
    }
}
