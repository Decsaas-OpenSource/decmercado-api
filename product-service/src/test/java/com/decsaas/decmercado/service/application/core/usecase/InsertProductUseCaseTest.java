package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.core.exception.ProductConflictException;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;
import com.decsaas.decmercado.service.application.ports.out.InsertProductOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.decsaas.decmercado.service.application.core.ProductConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsertProductUseCaseTest {

    @Mock
    FindProductOutputPort findProductOutputPort;

    @Mock
    InsertProductOutputPort insertProductOutputPort;

    private InsertProductUseCase useCase;

    @BeforeEach
    public void init() {
        useCase = new InsertProductUseCase(insertProductOutputPort, findProductOutputPort);
    }

    @Test()
    public void thereMustBeProductWithTheSameName() {
        when(findProductOutputPort.findByDescription(PRODUCT_USER_ID, PRODUCT_DESCRIPTION))
                .thenReturn(new Product(PRODUCT_USER_ID, PRODUCT_OTHER_ID, PRODUCT_DESCRIPTION));

        ProductConflictException productConflictException = assertThrows(ProductConflictException.class,
                () -> useCase.insert(new Product(PRODUCT_USER_ID, null, PRODUCT_DESCRIPTION))
        );

        assertNotNull(productConflictException);
        assertEquals(productConflictException.getMessage(), "Já existe um produto com a mesma descrição!");
    }

    @Test()
    public void mustInsertProduct() {
        Product productInsert = new Product(PRODUCT_USER_ID, null, PRODUCT_DESCRIPTION);
        Product productExpected = new Product(PRODUCT_USER_ID, PRODUCT_ID, PRODUCT_DESCRIPTION);
        when(insertProductOutputPort.insert(productInsert)).thenReturn(productExpected);

        Product result = useCase.insert(productInsert);

        assertEquals(productExpected.getUserId(), result.getUserId());
        assertEquals(productExpected.getId(), result.getId());
        assertEquals(productExpected.getDescription(), result.getDescription());
    }
}