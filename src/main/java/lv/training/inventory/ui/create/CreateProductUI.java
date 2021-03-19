package lv.training.inventory.ui.create;

import lv.training.inventory.database.Database;
import lv.training.inventory.service.ProductService;
import lv.training.inventory.ui.common.Utils;

public class CreateProductUI {

    Utils utils = new Utils();

    public void createProduct(ProductService service, Database db) {
        service.create(utils.productInput(), db);
    }
}
