package lv.training.inventory.ui;

import lv.training.inventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserMenuTest {

    @Mock
    ProductService serviceMock;

    @InjectMocks
    UserMenu userMenu;

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