package com.decsaas.decmercado.service.application.core.usecase;

import com.decsaas.decmercado.service.application.core.domain.Product;
import com.decsaas.decmercado.service.application.core.exception.ProductConflictException;
import com.decsaas.decmercado.service.application.core.exception.ProductNotFoundException;
import com.decsaas.decmercado.service.application.ports.out.EditProductOutputPort;
import com.decsaas.decmercado.service.application.ports.out.FindProductOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditProductUseCaseTest {

    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_USER_ID = "user";
    public static final String PRODUCT_ID = "id";
    public static final String PRODUCT_OTHER_ID = "id2";
    public static final String PRODUCT_OTHER_DESCRIPTION = "descricao";

    @Mock
    FindProductOutputPort findProductOutputPort;

    @Mock
    EditProductOutputPort editProductOutputPort;

    @Test()
    public void mustNotFindTheProduct() {
        EditProductUseCase useCase =
                new EditProductUseCase(null, findProductOutputPort);

        ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> useCase.edit(new Product(PRODUCT_USER_ID, null, PRODUCT_DESCRIPTION))
        );

        assertNotNull(productNotFoundException);
        assertEquals(productNotFoundException.getMessage(), "Produto não encontrado!");
    }

    @Test()
    public void thereMustBeProductWithTheSameName() {
        when(findProductOutputPort.findById(PRODUCT_USER_ID, PRODUCT_ID))
                .thenReturn(new Product(PRODUCT_USER_ID, PRODUCT_ID, PRODUCT_DESCRIPTION));

        when(findProductOutputPort.findByDescription(PRODUCT_USER_ID, PRODUCT_DESCRIPTION))
                .thenReturn(new Product(PRODUCT_USER_ID, PRODUCT_OTHER_ID, PRODUCT_DESCRIPTION));

        EditProductUseCase useCase = new EditProductUseCase(null, findProductOutputPort);

        ProductConflictException productConflictException = assertThrows(ProductConflictException.class,
                () -> useCase.edit(new Product(PRODUCT_USER_ID, PRODUCT_ID, PRODUCT_DESCRIPTION))
        );

        assertNotNull(productConflictException);
        assertEquals(productConflictException.getMessage(), "Já existe um produto com a mesma descrição!");
    }

    @Test()
    public void mustFindTheProduct() {
        when(editProductOutputPort.edit(any(Product.class)))
                .thenReturn(new Product(PRODUCT_USER_ID, PRODUCT_ID, PRODUCT_OTHER_DESCRIPTION));

        when(findProductOutputPort.findById(PRODUCT_USER_ID, PRODUCT_ID))
                .thenReturn(new Product(PRODUCT_USER_ID, PRODUCT_ID, PRODUCT_DESCRIPTION));

        when(findProductOutputPort.findByDescription(PRODUCT_USER_ID, PRODUCT_DESCRIPTION))
                .thenReturn(new Product(PRODUCT_USER_ID, PRODUCT_ID, PRODUCT_DESCRIPTION));

        EditProductUseCase useCase = new EditProductUseCase(editProductOutputPort,
                findProductOutputPort);

        Product result = useCase.edit(new Product(PRODUCT_USER_ID, PRODUCT_ID, PRODUCT_OTHER_DESCRIPTION));

        assertEquals(PRODUCT_USER_ID, result.getUserId());
        assertEquals(PRODUCT_ID, result.getId());
        assertEquals(PRODUCT_OTHER_DESCRIPTION, result.getDescription());
    }
}