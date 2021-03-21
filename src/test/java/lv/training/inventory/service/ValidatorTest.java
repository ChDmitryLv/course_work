package lv.training.inventory.service;

import lv.training.inventory.exceptions.NotLessThanZero;
import lv.training.inventory.exceptions.ProductNotFound;
import lv.training.inventory.model.Category;
import lv.training.inventory.model.ProductInput;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    Validator validator = new Validator();

    @Test
    void validateProductInput() {
        ProductInput productInput = new ProductInput("Pasta", BigDecimal.valueOf(-10), Category.MEAL);

        Exception exception = assertThrows(NotLessThanZero.class, () -> validator.validateProductInput(productInput));
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
}