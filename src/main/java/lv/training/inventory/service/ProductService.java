package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;

import java.util.List;

public interface ProductService {
    void create(ProductInput productInput, Database db);
    Product find(Integer id, Database db);
    List<Product> printAll(Database db);
    void update(Database db, ProductInput updatedProduct, Integer Id);
    void deleteProduct(Database db, Integer id);
}
