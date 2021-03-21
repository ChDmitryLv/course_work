package lv.training.inventory.ui;

import lv.training.inventory.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface UIOperations {

    String titleInput();
    BigDecimal priceInput();
    int idInput();
    int categoryInput();
    void printAll(List<Product> list);
    void printResult(Product product);
}
