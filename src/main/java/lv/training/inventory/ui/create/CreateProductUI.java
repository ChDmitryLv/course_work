package lv.training.inventory.ui.create;

import lv.training.inventory.service.ProductService;
import lv.training.inventory.service.ProductServiceImpl;

public class CreateProductUI {

    public void createProduct(ProductService service) {
        service.create();
    }
}
