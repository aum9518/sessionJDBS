package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Phone {
    private long id;
    private String model;
    private String brand;
    private int price;
    private Long userId;

    public Phone(String model, String brand, int price, Long userId) {
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.userId = userId;
    }

    public Phone(String model, String brand, int price) {
        this.model = model;
        this.brand = brand;
        this.price = price;
    }
}
