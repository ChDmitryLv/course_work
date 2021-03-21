package lv.training.inventory.ui.common;

import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.ui.InventoryUI;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Utils implements InventoryUI {

    public Utils() {
    }

    static Scanner sc = new Scanner(System.in);

    @Override
    public String titleInput() {
        String title;
        System.out.println("Set title: ");
        title = sc.next();
        return title;
    }

    @Override
    public BigDecimal priceInput() {
        BigDecimal price;
        System.out.println("Product price:");
        while (!sc.hasNextBigDecimal()) {
            System.out.println("Price must be BigDecimal");
            sc.next();
        }
        price = sc.nextBigDecimal();
        return price;
    }

    @Override
    public int idInput() {
        int id;
        System.out.println("Enter ID:");
        while (!sc.hasNextInt()) {
            System.out.println("ID must be integer");
            sc.next();
        }
        id = sc.nextInt();
        return id;
    }

    @Override
    public int categoryInput() {
        int categoryNumber;
        do {
            System.out.println("Choose category: 1. FRUIT 2. DRINK 3. MEAL");
            while (!sc.hasNextInt()) {
                System.out.println("Only number allowed");
                sc.next();
            }
            categoryNumber = sc.nextInt();
        } while (categoryNumber < 1 || categoryNumber > 3);
        return categoryNumber;
    }

    @Override
    public void printAll(List<Product> list) {
        for (Product it : list) {
            printResult(it);
        }
    }

    @Override
    public void printResult(Product product) {
        System.out.println(product);
    }
}
