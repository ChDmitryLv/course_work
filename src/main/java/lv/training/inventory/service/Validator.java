package lv.training.inventory.service;

import lv.training.inventory.exceptions.NoEmptyTitle;
import lv.training.inventory.exceptions.NotExistingCategory;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.model.Product;

import java.math.BigDecimal;

public class Validator {

void validateId(int id) throws NotLessThanZero {
    if (id<0){
        throw new NotLessThanZero("ID must be greater than 0");
    }
}

void notNull(Product product) throws ProductNotFound {
    if (product == null){
        throw new ProductNotFound("Product not found");
    }
}

void notEmpty(String title) throws NoEmptyTitle {
    if (title.isEmpty()){
        throw new NoEmptyTitle("Title can't be empty");
    }
}

void validateProductPrice(BigDecimal price) throws NotLessThanZero {
    if (price.compareTo(BigDecimal.ZERO) < 0){
        throw new NotLessThanZero("Price must be greater than 0");
    }
}

void validateCategoryInput(int input) throws NotExistingCategory {
    if(input < 1 || input > 3){
        throw new NotExistingCategory("Not existing category");
    }
}

}
