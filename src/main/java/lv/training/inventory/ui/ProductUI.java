package lv.training.inventory.ui;

import lv.training.inventory.database.Database;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class ProductUI {
    public void start(Database db, ProductService service) {
        boolean status = true;
        while (status) {
            menu();
            switch (chooseOperation()) {
                case 1 -> createProduct(service, db);
                case 2 -> findProduct(service, db);
                case 3 -> printAll(service, db);
                case 4 -> updateProduct(service, db);
                case 5 -> deleteProduct(service, db);
                case 0 -> status = false;
                default -> {}
            }
        }
    }

    private int chooseOperation() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void menu() {
        System.out.println("Choose operation:");
        System.out.println("1. save product");
        System.out.println("2. Find product by Id");
        System.out.println("3. Print All Products");
        System.out.println("4. Change product by Id");
        System.out.println("5. Delete product by Id");
        System.out.println(" ---------------------- ");
        System.out.println("0. Exit");
    }

    public void printResult(Product product){
        System.out.println(product);
    }

    public void printResult(List<Product> list){
        for (Product it: list){
            printResult(it);
        }
    }

    ProductInput productInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Product title: ");
        String name = sc.nextLine();
        System.out.println("Product price: ");
        BigDecimal price = sc.nextBigDecimal();
        System.out.println("Choose category: 1. FRUIT 2. DRINK 3. MEAL");
        int categoryNumber = sc.nextInt();
        Category category = switch (categoryNumber) {
            case 1 -> Category.FRUIT;
            case 2 -> Category.DRINK;
            case 3 -> Category.MEAL;
            default -> throw new IllegalStateException("Unexpected value: " + categoryNumber);
        };
        // add validator
        return new ProductInput(name, price, category);
    }

    void createProduct(ProductService service, Database db){
        service.create(productInput(),db);
    }

    void findProduct(ProductService service, Database db) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please provide Id:");
        Integer id = sc.nextInt();
        Product product = service.find(id, db);
        printResult(product);
    }

    void printAll(ProductService service, Database db){
        List<Product> list = service.printAll(db);
        printResult(list);
    }

    void updateProduct(ProductService service, Database db){
        Scanner sc = new Scanner(System.in);
        System.out.println("Set product Id to change");
        int id = sc.nextInt();
        service.find(id, db);
        ProductInput updatedProduct = productInput();
        service.update(db,updatedProduct,id);
    }

    void deleteProduct(ProductService service, Database db){
        printAll(service,db);
        System.out.println("Choose product Id to delete: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        service.deleteProduct(db, id);
    }

}


