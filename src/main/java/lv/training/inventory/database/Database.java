package lv.training.inventory.database;

import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;

import java.util.List;

public interface Database {
    void create(Product product);
    Product read(Integer id);
    List<Product> readAll();
    void update(ProductInput product, Integer id);
    void delete(Integer id);
}
