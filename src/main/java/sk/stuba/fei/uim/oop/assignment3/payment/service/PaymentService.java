package sk.stuba.fei.uim.oop.assignment3.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.payment.data.IPaymentRepository;
import sk.stuba.fei.uim.oop.assignment3.payment.data.Payment;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository repository;

    @Override
    public Payment savePayment(Payment payment) {
        return this.repository.save(payment);
    }
}
