package org.haiilo.controller.mapper;

import org.haiilo.datatransferobject.ProductDTO;
import org.haiilo.domainobject.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class ProductMapperTest {

    @Test
    void makeProductDTO_shouldMapAllFields() {
        Product product = new Product(1L, "Laptop Stand", new BigDecimal("29.90"));

        ProductDTO dto = ProductMapper.makeProductDTO(product);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.name()).isEqualTo("Laptop Stand");
        assertThat(dto.priceAmount()).isEqualByComparingTo("29.90");
    }

    @Test
    void makeProductDTOList_shouldMapAllProductsInOrder() {
        List<Product> products = List.of(
                new Product(1L, "Laptop Stand", new BigDecimal("29.90")),
                new Product(2L, "Wireless Mouse", new BigDecimal("19.99"))
        );

        List<ProductDTO> result = ProductMapper.makeProductDTOList(products);

        assertThat(result)
                .extracting(ProductDTO::id, ProductDTO::name, ProductDTO::priceAmount)
                .containsExactly(tuple(1L, "Laptop Stand", new BigDecimal("29.90")),
                        tuple(2L, "Wireless Mouse", new BigDecimal("19.99"))
                );
    }

    @Test
    void makeProduct_shouldMapAllFields() {
        ProductDTO productDTO = new ProductDTO(3L, "Mechanical Keyboard", new BigDecimal("89.50"));

        Product product = ProductMapper.makeProduct(productDTO);

        assertThat(product.getId()).isEqualTo(3L);
        assertThat(product.getName()).isEqualTo("Mechanical Keyboard");
        assertThat(product.getPriceAmount()).isEqualByComparingTo("89.50");
    }

    @Test
    void makeProductList_shouldMapAllProductsInOrder() {
        List<ProductDTO> productDTOs = List.of(
                new ProductDTO(1L, "Laptop Stand", new BigDecimal("29.90")),
                new ProductDTO(2L, "Wireless Mouse", new BigDecimal("19.99"))
        );

        List<Product> result = ProductMapper.makeProductList(productDTOs);

        assertThat(result)
                .extracting(Product::getId, Product::getName, Product::getPriceAmount)
                .containsExactly(
                        tuple(1L, "Laptop Stand", new BigDecimal("29.90")),
                        tuple(2L, "Wireless Mouse", new BigDecimal("19.99"))
                );
    }
}
