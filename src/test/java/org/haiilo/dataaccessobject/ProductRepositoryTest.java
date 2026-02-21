package org.haiilo.dataaccessobject;

import org.junit.jupiter.api.Test;
import org.haiilo.domainobject.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@DataJpaTest(properties = "spring.sql.init.mode=never")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql("/data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAll_shouldReturnAllProducts() {
        List<Product> products = productRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getId))
                .toList();

        assertThat(products).hasSize(5);
        assertThat(products)
                .extracting(Product::getId, Product::getName, Product::getPriceAmount)
                .containsExactly(
                        tuple(1L, "Laptop Stand", new BigDecimal("29.90")),
                        tuple(2L, "Wireless Mouse", new BigDecimal("19.99")),
                        tuple(3L, "Mechanical Keyboard", new BigDecimal("89.50")),
                        tuple(4L, "USB-C Hub", new BigDecimal("34.00")),
                        tuple(5L, "Noise Cancelling Headphones", new BigDecimal("129.00"))
                );
    }
}
