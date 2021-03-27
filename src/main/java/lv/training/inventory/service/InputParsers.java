package lv.training.inventory.service;

import lv.training.inventory.ui.UIOperations;

import java.math.BigDecimal;

public class InputParsers {
    private UIOperations ui;

    public InputParsers() {
    }

    public void setUi(UIOperations ui) {
        this.ui = ui;
    }

    int parseId() throws NumberFormatException{
        String rawString = ui.retrieveUserInput("Enter Id");
        return Integer.parseInt(rawString);
    }

    BigDecimal parseBigDecimal() throws NullPointerException, NumberFormatException {
        String rawString = ui.retrieveUserInput("Enter Price");
        double priceDouble = Double.parseDouble(rawString);
        return BigDecimal.valueOf(priceDouble);
    }

    int parseIntForCategory() throws NumberFormatException{
        String rawString = ui.retrieveUserInput("Choose category: 1. FRUIT 2. DRINK 3. MEAL");
        return Integer.parseInt(rawString);
    }
}

