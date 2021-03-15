package lv.training.inventory.model;

import java.math.BigDecimal;

public class ProductInput {
    private final String name;
    private final BigDecimal price;
    private final Category category;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public ProductInput(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
