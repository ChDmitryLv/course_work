package lv.training.inventory.exceptions;

public class ProductNotFound extends Exception {
    public ProductNotFound(String errorMessage){
        super(errorMessage);
    }
}
