package lv.training.inventory;

import lv.training.inventory.database.DataBaseFactory;
import lv.training.inventory.database.Database;
import lv.training.inventory.service.ProductService;
import lv.training.inventory.service.ProductServiceImpl;
import lv.training.inventory.ui.menu.UserMenu;


public class Application {
    public static void main(String[] args) {
        @SuppressWarnings({"AccessStaticViaInstance", "InstantiationOfUtilityClass"})
        Database db = new DataBaseFactory().createDatabase();
        ProductService service = new ProductServiceImpl();
        UserMenu menu = new UserMenu(db, service);
        menu.start();
    }
}
