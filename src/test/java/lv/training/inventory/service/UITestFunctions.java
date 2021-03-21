package lv.training.inventory.service;

import lv.training.inventory.model.Category;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;
import lv.training.inventory.ui.InventoryUI;

import java.math.BigDecimal;
import java.util.List;

public class UITestFunctions implements InventoryUI {

    private int id;
    private String printAllCalled;
    private String title;
    private BigDecimal price;
    private int categoryNumber;

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrintAllCalled() {
        return printAllCalled;
    }

    @Override
    public String titleInput() {
        return title;
    }

    @Override
    public BigDecimal priceInput() {
        return price;
    }

    @Override
    public int idInput() {
        return id;
    }

    @Override
    public int categoryInput() {
        return categoryNumber;
    }

    @Override
    public void printAll(List<Product> list) {
        printAllCalled = "Successfully called";
    }

    @Override
    public void printResult(Product product) {

    }
}
