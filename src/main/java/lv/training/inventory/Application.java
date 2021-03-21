package lv.training.inventory;

import lv.training.inventory.database.DataBaseFactory;
import lv.training.inventory.database.Database;
import lv.training.inventory.service.ProductService;
import lv.training.inventory.service.ProductServiceImpl;
import lv.training.inventory.ui.UIOperations;
import lv.training.inventory.ui.UIOperationsImpl;
import lv.training.inventory.ui.UserMenu;


public class Application {
    public static void main(String[] args) {
        @SuppressWarnings({"AccessStaticViaInstance", "InstantiationOfUtilityClass"})
        Database db = new DataBaseFactory().createDatabase();
        UIOperations utils = new UIOperationsImpl();
        ProductService service = new ProductServiceImpl(utils, db);

        UserMenu menu = new UserMenu(service);

        menu.start();
    }
}
