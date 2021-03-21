package lv.training.inventory.exceptions;


public class NotLessThanZero extends Exception {
    public NotLessThanZero(String errorMessage){
        super(errorMessage);
    }
}