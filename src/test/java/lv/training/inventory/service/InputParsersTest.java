package lv.training.inventory.service;

import lv.training.inventory.model.Category;
import lv.training.inventory.ui.UIOperations;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputParsersTest {

    UIOperations mockedUi = mock(UIOperations.class);
    InputParsers parsers = new InputParsers();

    @Test
    void parseId() {
        parsers.setUi(mockedUi);
        when(mockedUi.retrieveUserInput("Enter Id")).thenReturn("1");
        int id = parsers.parseId();
        assertEquals(1,id);

        when(mockedUi.retrieveUserInput("Enter Id")).thenReturn("not_number");
        Exception exception = assertThrows(NumberFormatException.class, () -> parsers.parseId());
        assertEquals("For input string: \"not_number\"",exception.getMessage());
    }

    @Test
    void parseBigDecimal() {
        parsers.setUi(mockedUi);
        when(mockedUi.retrieveUserInput("Enter Price")).thenReturn("10.0");
        BigDecimal price = parsers.parseBigDecimal();
        assertEquals(BigDecimal.valueOf(10.0),price);

        when(mockedUi.retrieveUserInput("Enter Price")).thenReturn("not_number");
        Exception exception = assertThrows(NumberFormatException.class, () -> parsers.parseBigDecimal());
        assertEquals("For input string: \"not_number\"",exception.getMessage());
    }

    @Test
    void parseIntForCategory() {
        parsers.setUi(mockedUi);
        when(mockedUi.retrieveUserInput("Choose category: 1. FRUIT 2. DRINK 3. MEAL")).thenReturn("2");
        int chooseCategory = parsers.parseIntForCategory();
        assertEquals(2, chooseCategory);

        when(mockedUi.retrieveUserInput("Choose category: 1. FRUIT 2. DRINK 3. MEAL")).thenReturn("not_number");
        Exception exception = assertThrows(NumberFormatException.class, () -> parsers.parseIntForCategory());
        assertEquals("For input string: \"not_number\"",exception.getMessage());
    }
}