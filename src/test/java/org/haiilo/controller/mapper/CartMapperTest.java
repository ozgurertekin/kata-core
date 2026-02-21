package org.haiilo.controller.mapper;

import org.haiilo.datatransferobject.CartItemDTO;
import org.haiilo.datatransferobject.ProductDTO;
import org.haiilo.serviceobject.CartItemSO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class CartMapperTest {

    @Test
    void makeCardSO_shouldMapAllFields() {
        CartItemDTO cartItemDTO = new CartItemDTO(
                new ProductDTO(1L, "Laptop Stand", new BigDecimal("29.90")),
                2
        );

        CartItemSO cartItemSO = CartMapper.makeCardSO(cartItemDTO);

        assertThat(cartItemSO.product().getId()).isEqualTo(1L);
        assertThat(cartItemSO.product().getName()).isEqualTo("Laptop Stand");
        assertThat(cartItemSO.product().getPriceAmount()).isEqualByComparingTo("29.90");
        assertThat(cartItemSO.purchasedAmount()).isEqualTo(2);
    }

    @Test
    void makeCartSOs_shouldMapAllItems() {
        List<CartItemDTO> input = List.of(
                new CartItemDTO(new ProductDTO(1L, "Laptop Stand", new BigDecimal("29.90")), 2),
                new CartItemDTO(new ProductDTO(2L, "Wireless Mouse", new BigDecimal("19.99")), 1)
        );

        List<CartItemSO> result = CartMapper.makeCartSOs(input);

        assertThat(result)
                .extracting(
                        item -> item.product().getId(),
                        item -> item.product().getName(),
                        item -> item.product().getPriceAmount(),
                        CartItemSO::purchasedAmount
                )
                .containsExactly(
                        tuple(1L, "Laptop Stand", new BigDecimal("29.90"), 2),
                        tuple(2L, "Wireless Mouse", new BigDecimal("19.99"), 1)
                );
    }
}
