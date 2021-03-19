package lv.training.inventory.service;

import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.model.Product;
import lv.training.inventory.model.ProductInput;

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

void validateProductInput(ProductInput productInput) throws NotLessThanZero {
    if (productInput.getPrice().compareTo(BigDecimal.ZERO) < 0){
        throw new NotLessThanZero("Price must be greater than 0");
    }
}

}
