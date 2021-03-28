package lv.training.inventory.database;

import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;

import java.math.BigDecimal;

public class DefaultDbInitializer {
    public void initDb(Database db){
        Product product = new Product();
        product.setId(1);
        product.setName("Apple");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(Category.FRUIT);
        db.create(product);
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Orange");
        product2.setPrice(BigDecimal.TEN);
        product2.setCategory(Category.FRUIT);
        db.create(product2);
    }
}
