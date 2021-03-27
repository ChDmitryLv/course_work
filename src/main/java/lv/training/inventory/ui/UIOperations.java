package lv.training.inventory.ui;

import lv.training.inventory.model.Product;

public interface UIOperations {

    String retrieveUserInput(String messageForTitle);
    void printResult(Product product);
}
