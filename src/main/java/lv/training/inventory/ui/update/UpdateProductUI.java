package lv.training.inventory.ui.update;

import lv.training.inventory.database.Database;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.service.ProductService;
import lv.training.inventory.ui.common.Utils;

import java.util.Scanner;

public class UpdateProductUI {

    Utils utils = new Utils();

    public void updateProduct(ProductService service, Database db) {
        int id = utils.idInput();
        if (null != service.find(id, db)) {
            ProductInput updatedProduct = utils.productInput();
            service.update(db, updatedProduct, id);
        }
    }
}
