package lv.training.inventory.ui.delete;

import lv.training.inventory.database.Database;
import lv.training.inventory.service.ProductService;
import lv.training.inventory.ui.common.Utils;

public class DeleteProductUI {

    Utils utils = new Utils();

    public void deleteProduct(ProductService service, Database db) {
        utils.printAll(service, db);
        int id = utils.idInput();
        service.deleteProduct(db, id);
    }
}
