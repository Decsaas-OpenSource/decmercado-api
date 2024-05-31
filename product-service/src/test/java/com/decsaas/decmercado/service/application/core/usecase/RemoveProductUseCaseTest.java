package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.core.exception.ProductNotFoundException;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;
import com.decsaas.decmercado.service.application.ports.out.RemoveProductOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.decsaas.decmercado.service.application.core.ProductConstants.PRODUCT_ID;
import static com.decsaas.decmercado.service.application.core.ProductConstants.PRODUCT_USER_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RemoveProductUseCaseTest {

    @Mock
    FindProductOutputPort findProductOutputPort;

    @Mock
    RemoveProductOutputPort removeProductOutputPort;

    RemoveProductUseCase useCase;

    @BeforeEach
    public void init() {
        useCase = new RemoveProductUseCase(removeProductOutputPort, findProductOutputPort);
    }

    @Test()
    public void mustNotFindTheProduct() {
        when(findProductOutputPort.findById(PRODUCT_USER_ID, PRODUCT_ID))
                .thenReturn(null);

        ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> useCase.remove(PRODUCT_USER_ID, PRODUCT_ID)
        );

        assertNotNull(productNotFoundException);
        assertEquals(productNotFoundException.getMessage(), "Produto não encontrado!");
    }

    @Test()
    public void mustDeleteTheProduct() {
        when(findProductOutputPort.findById(eq(PRODUCT_USER_ID), eq(PRODUCT_ID)))
                .thenReturn(mock(Product.class));

        useCase.remove(PRODUCT_USER_ID, PRODUCT_ID);

        verify(removeProductOutputPort, times(1))
                .remove(PRODUCT_USER_ID, PRODUCT_ID);
    }

}