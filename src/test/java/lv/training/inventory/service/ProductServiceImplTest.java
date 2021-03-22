package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.database.InMemoryDb;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    Database db = initDb();
    UIOperationsTestFunctions testUI = new UIOperationsTestFunctions();
    ProductService service = new ProductServiceImpl(testUI,db);

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
        testUI.setId(1);

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
        String shouldBeNull = testUI.getPrintAllCalled();
        assertNull(shouldBeNull);

        service.readAll();
        String expectedSuccess = testUI.getPrintAllCalled();

        assertEquals("Successfully called",expectedSuccess);
    }

    @Test
    void update(){
        testUI.setId(2);
        testUI.setTitle("Cola");
        testUI.setPrice(BigDecimal.valueOf(9.99));
        testUI.setCategoryNumber(2);

        Product beforeUpdate = db.read(2);
        assertEquals("Orange",beforeUpdate.getName());
        assertEquals(BigDecimal.TEN, beforeUpdate.getPrice());
        assertEquals(Category.FRUIT,beforeUpdate.getCategory());

        service.update();

        Product afterUpdate = db.read(2);
        assertEquals("Cola",afterUpdate.getName());
        assertEquals(BigDecimal.valueOf(9.99), afterUpdate.getPrice());
        assertEquals(Category.DRINK,afterUpdate.getCategory());
    }

    @Test
    void create(){
        Product beforeCreate = db.read(3);
        assertNull(beforeCreate);

        testUI.setTitle("Bacon");
        testUI.setPrice(BigDecimal.valueOf(3L));
        testUI.setCategoryNumber(3);

        service.create();

        Product afterCreate = db.read(3);
        assertEquals("Bacon",afterCreate.getName());
        assertEquals(BigDecimal.valueOf(3L), afterCreate.getPrice());
        assertEquals(Category.MEAL,afterCreate.getCategory());
    }

    @Test
    void delete(){
        Product beforeDelete = db.read(1);
        assertEquals(1,beforeDelete.getId());
        assertEquals("Apple",beforeDelete.getName());
        assertEquals(BigDecimal.ONE,beforeDelete.getPrice());
        assertEquals(Category.FRUIT,beforeDelete.getCategory());

        testUI.setId(1);
        service.deleteProduct();

        Product afterDelete = db.read(1);
        assertNull(afterDelete);
    }

    @Test
    void productInput(){
        ProductServiceImpl service = new ProductServiceImpl(testUI,db);
        testUI.setTitle("Banana");
        testUI.setPrice(BigDecimal.valueOf(1.99));
        testUI.setCategoryNumber(1);
        ProductInput result = service.productInput();

        assertEquals("Banana", result.getName());
        assertEquals(BigDecimal.valueOf(1.99), result.getPrice());
        assertEquals(Category.FRUIT, result.getCategory());

        testUI.setTitle("Kiwi");
        testUI.setPrice(BigDecimal.valueOf(1.99));
        testUI.setCategoryNumber(7);

        Exception illegalStateException = assertThrows(IllegalStateException.class, service::productInput);

        assertEquals("Unexpected value: 7",illegalStateException.getMessage());
    }
}