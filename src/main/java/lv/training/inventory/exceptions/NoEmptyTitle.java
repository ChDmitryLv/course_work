package lv.training.inventory.exceptions;

public class NoEmptyTitle extends Exception{
    public NoEmptyTitle(String errorMessage){
        super(errorMessage);
    }
}
