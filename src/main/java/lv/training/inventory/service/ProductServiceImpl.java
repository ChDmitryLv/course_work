package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.ui.common.Utils;

import java.util.List;


public class ProductServiceImpl implements ProductService {

    private static int counter = 1;
    Utils utils = new Utils();
    Validator validator = new Validator();

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
    public void update(Database db) throws NotLessThanZero, ProductNotFound {
        int id = utils.idInput();
        validator.validateId(id);
        Product product = find(id, db);
        validator.notNull(product);
        ProductInput productInput = utils.productInput();
        validator.validateProductInput(productInput);
        db.update(productInput, id);
    }

    @Override
    public void deleteProduct(Database db, Integer id) {
        db.delete(id);
    }
}
