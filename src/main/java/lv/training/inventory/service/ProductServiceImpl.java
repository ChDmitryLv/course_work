package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.ui.UIOperations;

import java.math.BigDecimal;
import java.util.List;


public class ProductServiceImpl implements ProductService {

    private final UIOperations ui;
    private final Database db;
    private final InputParsers parser;

    public ProductServiceImpl(UIOperations ui, Database db, InputParsers parser) {
        this.ui = ui;
        this.db = db;
        this.parser = parser;
    }

    Validator validator = new Validator();

    @Override
    public void create() {
        try {
            ProductInput productInput = productInput();
            Product product = new Product();
            product.setName(productInput.getName());
            product.setPrice(productInput.getPrice());
            product.setCategory(productInput.getCategory());
            db.create(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find() {
        parser.setUi(ui);
        try {
            int id = parser.parseId();
            validator.validateId(id);
            Product product = db.read(id);
            validator.notNull(product);
            ui.printResult(product);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void readAll() {
        List<Product> list = db.readAll();
        for (Product it : list) {
            ui.printResult(it);
        }
    }

    @Override
    public void update() {
        try {
            Product product = find();
            validator.notNull(product);
            ProductInput productInput = productInput();
            db.update(productInput, product.getId());
        } catch (Exception notExistingCategory) {
            notExistingCategory.printStackTrace();
        }
    }

    @Override
    public void deleteProduct() {

        try {
            Product product = find();
            validator.notNull(product);
            db.delete(product.getId());
        } catch (ProductNotFound | NotLessThanZero e) {
            e.printStackTrace();
        }
    }

    public ProductInput productInput()
            throws Exception {
        parser.setUi(ui);
        String name = ui.retrieveUserInput("Input product title:");
        validator.notEmpty(name);
        BigDecimal price = parser.parseBigDecimal();
        validator.validateProductPrice(price);
        int categoryNumber = parser.parseIntForCategory();
        validator.validateCategoryInput(categoryNumber);
        Category category = switch (categoryNumber) {
            case 1 -> Category.FRUIT;
            case 2 -> Category.DRINK;
            case 3 -> Category.MEAL;
            default -> throw new IllegalStateException("Unexpected value: " + categoryNumber);
        };
        return new ProductInput(name, price, category);
    }
}
