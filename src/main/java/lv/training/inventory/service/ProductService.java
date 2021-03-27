package lv.training.inventory.service;

import lv.training.inventory.model.Product;

public interface ProductService {
    void create();
    Product find();
    void readAll();
    void update();
    void deleteProduct();
}
