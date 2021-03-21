package lv.training.inventory.service;

import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.model.Product;

public interface ProductService {
    void create();
    Product find() throws NotLessThanZero, ProductNotFound;
    void readAll();
    void update();
    void deleteProduct();
}
