package lv.training.inventory.exceptions;


public class NotLessThanZero extends RuntimeException {
    public NotLessThanZero(String errorMessage){
        super(errorMessage);
    }
}