package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;

import java.util.List;


public class ProductServiceImpl implements ProductService {

    private static int counter = 1;

    @Override
    public void create(ProductInput productInput, Database db) {
        Product product = new Product();
        product.setName(productInput.getName());
        product.setPrice(productInput.getPrice());
        product.setCategory(productInput.getCategory());
        product.setId(counter);
        counter++;

        db.create(product);
    }

    @Override
    public Product find(Integer id, Database db) {
        return db.read(id);
    }

    @Override
    public List<Product> printAll(Database db) {
        return db.readAll();
    }

    @Override
    public void update(Database db, ProductInput updatedProduct, Integer id) {
        db.update(updatedProduct, id);
    }

    @Override
    public void deleteProduct(Database db, Integer id) {
        db.delete(id);
    }
}
