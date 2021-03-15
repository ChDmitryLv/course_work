package lv.training.inventory;

import lv.training.inventory.database.DataBaseFactory;
import lv.training.inventory.database.Database;
import lv.training.inventory.service.ProductService;
import lv.training.inventory.service.ProductServiceImpl;
import lv.training.inventory.ui.ProductUI;


public class Application {
    public static void main(String[] args) {
        ProductUI ui = new ProductUI();
        @SuppressWarnings({"AccessStaticViaInstance", "InstantiationOfUtilityClass"})
        Database db = new DataBaseFactory().createDatabase();
        ProductService service = new ProductServiceImpl();
        ui.start(db, service);
    }
}
