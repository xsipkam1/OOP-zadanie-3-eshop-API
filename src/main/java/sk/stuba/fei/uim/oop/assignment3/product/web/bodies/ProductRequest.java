package sk.stuba.fei.uim.oop.assignment3.product.web.bodies;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private int amount;
    private String unit;
    private int price;
}
