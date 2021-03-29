package lv.training.inventory.ui;

import lv.training.inventory.service.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.Mockito.*;

class UserMenuTest {

    ProductService serviceMock = mock(ProductService.class);
    UserMenu userMenu = new UserMenu(serviceMock);

    @Test
    void start() {
        UserMenu userMenuSpy = Mockito.spy(userMenu);

        doReturn(57)
                .doReturn(5)
                .doReturn(4)
                .doReturn(3)
                .doReturn(2)
                .doReturn(1)
                .doReturn(0).when(userMenuSpy).chooseOperation();
        doNothing().when(userMenuSpy).menu();

        userMenuSpy.start();

        verify(serviceMock).create();
        verify(serviceMock).find();
        verify(serviceMock).readAll();
        verify(serviceMock).update();
        verify(serviceMock).deleteProduct();
        verify(userMenuSpy, atLeast(6)).chooseOperation();
        verify(userMenuSpy, atLeast(6)).menu();
    }
}