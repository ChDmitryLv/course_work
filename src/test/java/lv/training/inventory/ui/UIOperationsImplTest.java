package lv.training.inventory.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UIOperationsImplTest {

    @Mock
    Scanner sc;

    @InjectMocks
    UIOperationsImpl ui;

    @Test
    void retrieveUserInput() {
        when(sc.nextLine()).thenReturn(String.valueOf(10));
        String actual = ui.retrieveUserInput("ola");
        assertEquals("10",actual);
    }
}