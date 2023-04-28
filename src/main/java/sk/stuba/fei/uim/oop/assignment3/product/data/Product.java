package sk.stuba.fei.uim.oop.assignment3.product.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.payment.Payment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private int amount;
    private String unit;
    private int price;

    @OneToMany
    private List<Payment> payments = new ArrayList<>();

    public Product(String name, String description, int amount, String unit, int price) {
        this.name=name;
        this.description=description;
        this.amount=amount;
        this.unit=unit;
        this.price=price;
    }
}
