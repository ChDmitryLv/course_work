package lv.training.inventory.ui.find;

import lv.training.inventory.database.Database;
import lv.training.inventory.model.Product;
import lv.training.inventory.service.ProductService;
import lv.training.inventory.ui.common.Utils;

public class FindProductUI {

    Utils utils = new Utils();

    public void findProduct(ProductService service, Database db) {
        Integer id = utils.idInput();
        Product product = service.find(id, db);
        utils.printResult(product);
    }

}
