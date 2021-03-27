package lv.training.inventory.ui;

import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.service.ProductService;

import java.util.Scanner;

public class UserMenu {
    private final ProductService service;

    public UserMenu(ProductService service) {
        this.service = service;
    }

    static Scanner sc = new Scanner(System.in);

    public void start(){
        boolean status = true;
        while (status) {
            menu();
            switch (chooseOperation()) {
                case 1 -> service.create();
                case 2 -> service.find();
                case 3 -> service.readAll();
                case 4 -> service.update();
                case 5 -> service.deleteProduct();
                case 0 -> status = false;
                default -> {
                }
            }
        }
    }

    void menu() {
        System.out.println("Choose operation:");
        System.out.println("1. save product");
        System.out.println("2. Find product by Id");
        System.out.println("3. Print All Products");
        System.out.println("4. Change product by Id");
        System.out.println("5. Delete product by Id");
        System.out.println(" ---------------------- ");
        System.out.println("0. Exit");
    }

    private Integer chooseOperation() {
        int operationNumber;
        while (!sc.hasNextInt()) {
            System.out.println("Only number allowed");
            sc.next();
        }
        operationNumber = sc.nextInt();
        return operationNumber;
    }
}
