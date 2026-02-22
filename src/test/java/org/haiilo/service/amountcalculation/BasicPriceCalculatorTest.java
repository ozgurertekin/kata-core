package org.haiilo.service.amountcalculation;

import org.haiilo.domainobject.Offer;
import org.haiilo.domainobject.Product;
import org.haiilo.serviceobject.CartItemSO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BasicPriceCalculatorTest {

    private final BasicPriceCalculator calculator = new BasicPriceCalculator();

    @Test
    void calculate_shouldMultiplyUnitPriceByPurchasedAmount() {
        CartItemSO cartItem = new CartItemSO(
                new Product(1L, "Laptop Stand", new BigDecimal("29.90")),
                3
        );

        BigDecimal result = calculator.calculate(cartItem, new Offer());
        assertThat(result).isEqualByComparingTo("89.70");
    }
}
