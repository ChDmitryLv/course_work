package lv.training.inventory.ui.common;

import lv.training.inventory.database.Database;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Utils {

    static Scanner sc = new Scanner(System.in);

    public ProductInput productInput() {
        String name = titleInput();
        BigDecimal price = priceInput();
        int categoryNumber = categoryInput();
        Category category = switch (categoryNumber) {
            case 1 -> Category.FRUIT;
            case 2 -> Category.DRINK;
            case 3 -> Category.MEAL;
            default -> throw new IllegalStateException("Unexpected value: " + categoryNumber);
        };
        return new ProductInput(name, price, category);
    }

    public String titleInput() {
        String title;
        System.out.println("Set title: ");
        title = sc.next();
        return title;
    }

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

    public void printAll(ProductService service, Database db) {
        List<Product> list = service.printAll(db);
        printResult(list);
    }

    public void printResult(Product product) {
        System.out.println(product);
    }

    public void printResult(List<Product> list) {
        for (Product it : list) {
            printResult(it);
        }
    }
}
