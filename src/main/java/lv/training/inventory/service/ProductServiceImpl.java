package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.ui.InventoryUI;

import java.math.BigDecimal;


public class ProductServiceImpl implements ProductService {

    private final InventoryUI utils;
    private final Database db;

    public ProductServiceImpl(InventoryUI utils, Database db) {
        this.utils = utils;
        this.db = db;
    }

    private static int counter = 1;
    Validator validator = new Validator();

    @Override
    public void create() {
        ProductInput productInput = productInput();
        Product product = new Product();
        product.setName(productInput.getName());
        product.setPrice(productInput.getPrice());
        product.setCategory(productInput.getCategory());
        product.setId(counter);
        counter++;

        db.create(product);
    }

    @Override
    public Product find() throws NotLessThanZero, ProductNotFound {
        int id = utils.idInput();
        Product product;
            validator.validateId(id);
            product = db.read(id);
            validator.notNull(product);
            utils.printResult(product);

        return product;
    }

    @Override
    public void readAll() {
        utils.printAll(db.readAll());
    }

    @Override
    public void update() {
        Product product;
        try {
            product = find();
            validator.notNull(product);
            ProductInput productInput = productInput();
            validator.validateProductInput(productInput);
            db.update(productInput, product.getId());
        } catch (NotLessThanZero | ProductNotFound notLessThanZero) {
            notLessThanZero.printStackTrace();
        }
    }

    @Override
    public void deleteProduct()  {

        try {
            Product product = find();
            validator.notNull(product);
            db.delete(product.getId());
        } catch (ProductNotFound | NotLessThanZero productNotFound) {
            productNotFound.printStackTrace();
        }
    }

    public ProductInput productInput() {
        String name = utils.titleInput();
        BigDecimal price = utils.priceInput();
        int categoryNumber = utils.categoryInput();
        Category category = switch (categoryNumber) {
            case 1 -> Category.FRUIT;
            case 2 -> Category.DRINK;
            case 3 -> Category.MEAL;
            default -> throw new IllegalStateException("Unexpected value: " + categoryNumber);
        };
        return new ProductInput(name, price, category);
    }
}
