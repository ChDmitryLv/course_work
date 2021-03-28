package lv.training.inventory.ui;

import lv.training.inventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserMenuTest {
    @Mock
    ProductService serviceMock = mock(ProductService.class);

    @InjectMocks
    UserMenu userMenu = new UserMenu(serviceMock);

    @Test
    void start() {
        UserMenu userMenu1 = Mockito.spy(userMenu);

        doReturn(5)
                .doReturn(4)
                .doReturn(3)
                .doReturn(2)
                .doReturn(1)
                .doReturn(0).when(userMenu1).chooseOperation();
        userMenu1.start();

        verify(serviceMock).create();
        verify(serviceMock).find();
        verify(serviceMock).readAll();
        verify(serviceMock).update();
        verify(serviceMock).deleteProduct();
        verify(userMenu1, atLeast(6)).chooseOperation();

    }
}