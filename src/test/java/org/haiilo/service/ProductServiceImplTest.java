package org.haiilo.service;

import org.haiilo.dataaccessobject.ProductRepository;
import org.haiilo.domainobject.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findAll_shouldReturnProductsFromRepository() {
        List<Product> expected = List.of(
                new Product(1L, "Laptop Stand", new BigDecimal("29.90")),
                new Product(2L, "Wireless Mouse", new BigDecimal("19.99"))
        );
        when(productRepository.findAll()).thenReturn(expected);

        List<Product> result = productService.findAll();

        assertThat(result).isEqualTo(expected);
        verify(productRepository).findAll();
    }
}
