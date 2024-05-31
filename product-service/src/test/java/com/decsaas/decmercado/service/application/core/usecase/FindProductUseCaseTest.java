package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.core.exception.ProductIdNotProvided;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.decsaas.decmercado.service.application.core.ProductConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindProductUseCaseTest {

    @Mock
    FindProductOutputPort findProductOutputPort;

    FindProductUseCase useCase;

    @BeforeEach
    public void init() {
        useCase = new FindProductUseCase(findProductOutputPort);
    }

    @Test
    public void mustInformThatIdWasNotInformed() {
        ProductIdNotProvided productNotFoundException = assertThrows(ProductIdNotProvided.class,
                () -> useCase.findById(PRODUCT_USER_ID, null)
        );

        assertNotNull(productNotFoundException);
        assertEquals(productNotFoundException.getMessage(), "Id do produto não informado!");
    }

    @Test
    public void mustReturnProduct() {
        Product product = new Product(PRODUCT_USER_ID, PRODUCT_ID, PRODUCT_DESCRIPTION);

        when(findProductOutputPort.findById(PRODUCT_USER_ID, PRODUCT_ID)).
                thenReturn(product);

        Product result = useCase.findById(PRODUCT_USER_ID, PRODUCT_ID);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getUserId(), result.getUserId());
        assertEquals(product.getDescription(), result.getDescription());
    }

}