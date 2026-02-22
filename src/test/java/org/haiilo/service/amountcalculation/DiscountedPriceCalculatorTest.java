package org.haiilo.service.amountcalculation;

import org.haiilo.domainobject.Offer;
import org.haiilo.domainobject.Product;
import org.haiilo.serviceobject.CartItemSO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountedPriceCalculatorTest {

    private final DiscountedPriceCalculator calculator = new DiscountedPriceCalculator();

    @Test
    void calculate_shouldMultiplyDiscountedUnitPriceByPurchasedAmount() {
        CartItemSO cartItem = new CartItemSO(
                new Product(1L, "Mechanical Keyboard", new BigDecimal("89.50")),
                2
        );
        Offer offer = new Offer();
        offer.setAmount(new BigDecimal("59.99"));

        BigDecimal result = calculator.calculate(cartItem, offer);

        assertThat(result).isEqualByComparingTo("119.98");
    }
}
