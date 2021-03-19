package lv.training.inventory.ui.menu;

import lv.training.inventory.database.Database;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.service.ProductService;
import lv.training.inventory.ui.common.Utils;
import lv.training.inventory.ui.create.CreateProductUI;
import lv.training.inventory.ui.delete.DeleteProductUI;
import lv.training.inventory.ui.find.FindProductUI;

import java.util.Scanner;

public class UserMenu {
    private final Database db;
    private final ProductService service;


    public UserMenu(Database db, ProductService service) {
        this.db = db;
        this.service = service;
    }

    static Scanner sc = new Scanner(System.in);

    CreateProductUI createProductUI = new CreateProductUI();
    FindProductUI findProductUI = new FindProductUI();
    DeleteProductUI deleteProductUI = new DeleteProductUI();
    Utils utils = new Utils();

    public void start() {
        boolean status = true;
        while (status) {
            menu();
            switch (chooseOperation()) {
                case 1 -> createProductUI.createProduct(service, db);
                case 2 -> findProductUI.findProduct(service, db);
                case 3 -> utils.printAll(service, db);
                case 4 -> {
                    try {
                        service.update(db);
                    } catch (NotLessThanZero | ProductNotFound e) {
                        e.printStackTrace();
                    }
                }
                case 5 -> deleteProductUI.deleteProduct(service, db);
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
