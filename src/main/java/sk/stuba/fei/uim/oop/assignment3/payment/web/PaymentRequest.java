package sk.stuba.fei.uim.oop.assignment3.payment.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest {
    private Long productId;
    private int amount;
}
