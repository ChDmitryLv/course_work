package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.ui.UIOperations;

import java.math.BigDecimal;


public class ProductServiceImpl implements ProductService {

    private final UIOperations ui;
    private final Database db;

    public ProductServiceImpl(UIOperations ui, Database db) {
        this.ui = ui;
        this.db = db;
    }

    Validator validator = new Validator();

    @Override
    public void create() {
        try {
            ProductInput productInput = productInput();
            validator.validateProductInput(productInput);
            Product product = new Product();
            product.setName(productInput.getName());
            product.setPrice(productInput.getPrice());
            product.setCategory(productInput.getCategory());
            db.create(product);
        } catch (NotLessThanZero notLessThanZero) {
            notLessThanZero.printStackTrace();
        }
    }

    @Override
    public Product find() throws NotLessThanZero, ProductNotFound {
        int id = ui.idInput();
        Product product;
        validator.validateId(id);
        product = db.read(id);
        validator.notNull(product);
        ui.printResult(product);
        return product;
    }

    @Override
    public void readAll() {
        ui.printAll(db.readAll());
    }

    @Override
    public void update() {
        Product product;
        try {
            product = find();
            //validator.notNull(product);
            ProductInput productInput = productInput();
            validator.validateProductInput(productInput);
            db.update(productInput, product.getId());
        } catch (NotLessThanZero | ProductNotFound notLessThanZero) {
            notLessThanZero.printStackTrace();
        }
    }

    @Override
    public void deleteProduct() {

        try {
            Product product = find();
            validator.notNull(product);
            db.delete(product.getId());
        } catch (ProductNotFound | NotLessThanZero productNotFound) {
            productNotFound.printStackTrace();
        }
    }

    public ProductInput productInput() {
        String name = ui.titleInput();
        BigDecimal price = ui.priceInput();
        int categoryNumber = ui.categoryInput();
        Category category = switch (categoryNumber) {
            case 1 -> Category.FRUIT;
            case 2 -> Category.DRINK;
            case 3 -> Category.MEAL;
            default -> throw new IllegalStateException("Unexpected value: " + categoryNumber);
        };
        return new ProductInput(name, price, category);
    }
}
