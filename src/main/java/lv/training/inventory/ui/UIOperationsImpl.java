package lv.training.inventory.ui;

import lv.training.inventory.model.Product;

import java.util.Scanner;

public class UIOperationsImpl implements UIOperations {

    public UIOperationsImpl() {}

    @Override
    public String retrieveUserInput(String messageForTitle) {
        Scanner sc = new Scanner(System.in);
        System.out.println(messageForTitle);
        return sc.nextLine();
    }

    @Override
    public void printResult(Product product) {
        System.out.println(product);
    }
}
