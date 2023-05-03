package sk.stuba.fei.uim.oop.assignment3.payment.service;

import sk.stuba.fei.uim.oop.assignment3.payment.data.Payment;

public interface IPaymentService {
    Payment savePayment(Payment payment);
}
