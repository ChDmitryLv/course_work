package lv.training.inventory.exceptions;

public class NotExistingCategory extends Exception {
    public NotExistingCategory(String errorMessage){
        super(errorMessage);
    }
}
