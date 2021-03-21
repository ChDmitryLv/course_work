package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.database.InMemoryDb;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    Database initDb() {
        Database testDB = new InMemoryDb();
        Product product = new Product();
        product.setId(1);
        product.setName("Apple");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(Category.FRUIT);
        testDB.create(product);
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Orange");
        product2.setPrice(BigDecimal.TEN);
        product2.setCategory(Category.FRUIT);
        testDB.create(product2);
        return testDB;
    }

    @Test
    void find() throws NotLessThanZero, ProductNotFound {
        UIOperationsTestFunctions testUI = new UIOperationsTestFunctions();
        testUI.setId(1);
        Database db = initDb();
        ProductService service = new ProductServiceImpl(testUI,db);
        Product product = service.find();

        assertEquals(1,product.getId());
        assertEquals("Apple",product.getName());
        assertEquals(BigDecimal.ONE,product.getPrice());
        assertEquals(Category.FRUIT,product.getCategory());

        testUI.setId(-1);
        Exception lessThanZeroException = assertThrows(NotLessThanZero.class, service::find);

        assertEquals("ID must be greater than 0",lessThanZeroException.getMessage());

        testUI.setId(100);
        Exception notFoundException = assertThrows(ProductNotFound.class, service::find);

        assertEquals("Product not found",notFoundException.getMessage());
    }

    @Test
    void readAll(){
        Database db = initDb();
        UIOperationsTestFunctions testUI = new UIOperationsTestFunctions();
        ProductService service = new ProductServiceImpl(testUI,db);

        String shouldBeNull = testUI.getPrintAllCalled();
        assertNull(shouldBeNull);

        service.readAll();
        String expectedSuccess = testUI.getPrintAllCalled();

        assertEquals("Successfully called",expectedSuccess);
    }

    @Test
    void update(){
        UIOperationsTestFunctions testUI = new UIOperationsTestFunctions();
        testUI.setId(2);
        testUI.setTitle("Cola");
        testUI.setPrice(BigDecimal.valueOf(9.99));
        testUI.setCategoryNumber(2);
        Database db = initDb();
        ProductService service = new ProductServiceImpl(testUI,db);

        Product beforeUpdate = db.read(2);
        assertEquals("Orange",beforeUpdate.getName());
        assertEquals(BigDecimal.TEN, beforeUpdate.getPrice());
        assertEquals(Category.FRUIT,beforeUpdate.getCategory());

        service.update();

        Product afterUpdate = db.read(2);
        assertEquals("Cola",beforeUpdate.getName());
        assertEquals(BigDecimal.valueOf(9.99), beforeUpdate.getPrice());
        assertEquals(Category.DRINK,beforeUpdate.getCategory());
    }
}