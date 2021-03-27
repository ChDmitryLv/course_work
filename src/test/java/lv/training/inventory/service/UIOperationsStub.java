package lv.training.inventory.service;

import lv.training.inventory.model.Product;
import lv.training.inventory.ui.UIOperations;

public class UIOperationsStub implements UIOperations {

    private String whenMethodCalled;

    public String getWhenMethodCalled() {
        return whenMethodCalled;
    }
    @Override
    public void printResult(Product product) {
        whenMethodCalled = "method called";
    }

    @Override
    public String retrieveUserInput(String messageForTitle) {
        return messageForTitle;
    }
}
