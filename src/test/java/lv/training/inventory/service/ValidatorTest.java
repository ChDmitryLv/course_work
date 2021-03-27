package lv.training.inventory.service;

import lv.training.inventory.exceptions.NoEmptyTitle;
import lv.training.inventory.exceptions.NotExistingCategory;
import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    Validator validator = new Validator();

    @Test
    void validateProductPrice() {
        Exception exception = assertThrows(NotLessThanZero.class,
                () -> validator.validateProductPrice(BigDecimal.valueOf(-10.0)));
        assertEquals("Price must be greater than 0",exception.getMessage());
    }

    @Test
    void validateId() {
        Exception exception = assertThrows(NotLessThanZero.class, () -> validator.validateId(-10));
        assertEquals("ID must be greater than 0",exception.getMessage());
    }

    @Test
    void notNull() {
        Exception exception = assertThrows(ProductNotFound.class, () -> validator.notNull(null));
        assertEquals("Product not found",exception.getMessage());
    }

    @Test
    void notExistingCategory(){
        Exception exception = assertThrows(NotExistingCategory.class, () -> validator.validateCategoryInput(7));
        assertEquals("Not existing category",exception.getMessage());
    }

    @Test
    void notEmpty(){
        Exception exception = assertThrows(NoEmptyTitle.class, () -> validator.notEmpty(""));
        assertEquals("Title can't be empty",exception.getMessage());
    }
}