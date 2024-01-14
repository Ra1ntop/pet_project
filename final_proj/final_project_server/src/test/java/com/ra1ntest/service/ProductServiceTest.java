package com.ra1ntest.service;

import com.ra1ntest.exception.EntityNotFoundException;
import com.ra1ntest.exception.EntityUnexistsException;
import com.ra1ntest.exception.EntityUnexistsName;
import com.ra1ntest.exception.NonValidFieldDataException;
import com.ra1ntest.persistance.entity.product.Product;
import com.ra1ntest.repository.product.ProductRepository;
import com.ra1ntest.service.product.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.ra1ntest.util.ExceptionUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product = new Product();


    @Test
    public void shouldBeCreateProductThenProductNull() {
//        given
        product = null;
//        when
        EntityUnexistsException exception = Assertions.assertThrows(EntityUnexistsException.class, () -> productService.create(product));
//        then
        assertThat(exception).isInstanceOf(EntityUnexistsException.class);
        assertThat(exception.getMessage()).isEqualTo(PRODUCT_ENTITY_UNEXISTS_EXCEPTION);
    }

    @Test
    public void shouldBeCreatedThenProductNameIsNull() {
        product = new Product();
        product.setName(null);

        EntityUnexistsName exception = Assertions.assertThrows(EntityUnexistsName.class, () -> productService.create(product));

        assertThat(exception).isInstanceOf(EntityUnexistsName.class);
        assertThat(exception.getMessage()).isEqualTo(PRODUCT_ENTITY_UNEXISTS_NAME_EXCEPTION);
    }

    @Test
    public void shouldBeCreatedThenProductNameIsValid() {
        product = new Product();
        product.setName("Test name");

        productService.create(product);

        Mockito.verify(productRepository, Mockito.times(1)).save(product);

    }

    @Test
    public void shouldBeFindProductThenProductIdIsNull() {
        Long id = null;

        NonValidFieldDataException exception = Assertions.assertThrows(NonValidFieldDataException.class, () -> productService.findById(id));

        assertThat(exception).isInstanceOf(NonValidFieldDataException.class);
        assertThat(exception.getMessage()).isEqualTo(NON_VALID_FIELD_DATA_EXCEPTION);

    }

    @Test
    public void shouldBeFindProductThenProductIdIsNotExists() {
        Long id = 1L;

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> productService.findById(id));

        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(ENTITY_NOT_FOUND_EXCEPTION);

    }

    @Test
    public void shouldBeFindProductThenProductIdIsCorrect() {
        Long testId = 1L;
        product.setId(testId);
        Mockito.when(productRepository.findById(testId)).thenReturn(Optional.of(product));

        product = productService.findById(testId);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(testId);

    }

}
