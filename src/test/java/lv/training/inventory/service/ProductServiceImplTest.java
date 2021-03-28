package lv.training.inventory.service;

import lv.training.inventory.database.Database;
import lv.training.inventory.database.InMemoryDb;
import lv.training.inventory.exceptions.NotExistingCategory;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.ui.UIOperations;
import lv.training.inventory.ui.UIOperationsImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    Database db = initDb();
    UIOperations mockedUI = mock(UIOperationsImpl.class);
    InputParsers mockedParser = mock(InputParsers.class);
    ProductService service = new ProductServiceImpl(mockedUI, db, mockedParser);

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
    void find() {
        when(mockedParser.parseId()).thenReturn(1);

        Product product = service.find();

        assertEquals(1, product.getId());
        assertEquals("Apple", product.getName());
        assertEquals(BigDecimal.ONE, product.getPrice());
        assertEquals(Category.FRUIT, product.getCategory());
    }

    @Test
    void readAll(){
        Database mockedDb = mock(Database.class);
        ProductService service = new ProductServiceImpl(mockedUI,mockedDb,mockedParser);
        List<Product> tmpListOfProducts = db.readAll();
        when(mockedDb.readAll()).thenReturn(tmpListOfProducts);

        service.readAll();

        verify(mockedDb, atLeastOnce()).readAll();
        verify(mockedUI, atLeast(tmpListOfProducts.size())).printResult(any());
    }

    @Test
    void create() {
        when(mockedUI.retrieveUserInput("Input product title:")).thenReturn("Bacon");
        when(mockedParser.parseBigDecimal()).thenReturn(BigDecimal.ONE);
        when(mockedParser.parseIntForCategory()).thenReturn(3);
        ProductService myService = new ProductServiceImpl(mockedUI,db,mockedParser);

        myService.create();

        Product afterCreate = db.read(3);
        assertEquals("Bacon",afterCreate.getName());
        assertEquals(BigDecimal.ONE, afterCreate.getPrice());
        assertEquals(Category.MEAL,afterCreate.getCategory());
    }

    @Test
    void update(){
        when(mockedParser.parseId()).thenReturn(1);
        when(mockedUI.retrieveUserInput("Input product title:")).thenReturn("Cola");
        when(mockedParser.parseBigDecimal()).thenReturn(BigDecimal.TEN);
        when(mockedParser.parseIntForCategory()).thenReturn(2);

        Product productBeforeUpdate = db.read(1);
        assertEquals(1, productBeforeUpdate.getId());
        assertEquals("Apple", productBeforeUpdate.getName());
        assertEquals(BigDecimal.ONE, productBeforeUpdate.getPrice());
        assertEquals(Category.FRUIT, productBeforeUpdate.getCategory());

        service.update();

        Product productAfterUpdate = db.read(1);
        assertEquals(1, productAfterUpdate.getId());
        assertEquals("Cola",productAfterUpdate.getName());
        assertEquals(BigDecimal.TEN,productAfterUpdate.getPrice());
        assertEquals(Category.DRINK,productAfterUpdate.getCategory());
    }

    @Test
    void delete(){
        Product beforeDelete = db.read(2);
        assertEquals(2,beforeDelete.getId());
        assertEquals("Orange",beforeDelete.getName());
        assertEquals(BigDecimal.TEN,beforeDelete.getPrice());
        assertEquals(Category.FRUIT,beforeDelete.getCategory());
        when(mockedParser.parseId()).thenReturn(2);

        service.deleteProduct();

        Product afterDelete = db.read(2);
        assertNull(afterDelete);
    }

    @Test
    void productInput() throws Exception {
        ProductServiceImpl service = new ProductServiceImpl(mockedUI,db,mockedParser);

        when(mockedUI.retrieveUserInput("Input product title:")).thenReturn("Banana");
        when(mockedParser.parseBigDecimal()).thenReturn(BigDecimal.valueOf(1.99));
        when(mockedParser.parseIntForCategory()).thenReturn(1);
        ProductInput result = service.productInput();

        assertEquals("Banana", result.getName());
        assertEquals(BigDecimal.valueOf(1.99), result.getPrice());
        assertEquals(Category.FRUIT, result.getCategory());

        when(mockedParser.parseIntForCategory()).thenReturn(11);
        Exception e = assertThrows(NotExistingCategory.class, service::productInput);

        assertEquals("Not existing category",e.getMessage());
    }
}