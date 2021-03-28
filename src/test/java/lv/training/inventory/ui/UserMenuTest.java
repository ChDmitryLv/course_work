package lv.training.inventory.ui;

import lv.training.inventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class UserMenuTest {
    @Mock
    ProductService serviceMock = mock(ProductService.class);

    @InjectMocks
    UserMenu userMenu = new UserMenu(serviceMock);

    @Test
    void start() {
        UserMenu userMenuSpy = Mockito.spy(userMenu);

        doReturn(5)
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

    }
}