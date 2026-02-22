package org.haiilo.service.amountcalculation;

import org.haiilo.domainobject.Offer;
import org.haiilo.domainobject.Product;
import org.haiilo.serviceobject.CartItemSO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PercentagePriceCalculatorTest {

    private final PercentagePriceCalculator calculator = new PercentagePriceCalculator();

    @Test
    void calculate_shouldApplyPercentageOnCartTotal() {
        BigDecimal result = calculator.calculate(cartItem("29.90", 2), percentageOffer("10.00"));

        assertThat(result).isEqualByComparingTo("53.82");
    }

    @Test
    void calculate_shouldApplyPercentageOnCartTotal2() {
        BigDecimal result = calculator.calculate(cartItem("19.99", 3), percentageOffer("15.00"));

        assertThat(result).isEqualByComparingTo("50.97");
    }

    @Test
    void calculate_shouldReturnZeroForZeroPercent() {
        BigDecimal result = calculator.calculate(cartItem("89.50", 2), percentageOffer("0.00"));
        assertThat(result).isEqualByComparingTo("179.00");
    }

    private static CartItemSO cartItem(String price, int purchasedAmount) {
        return new CartItemSO(new Product(1L, "Test Product", new BigDecimal(price)), purchasedAmount);
    }

    private static Offer percentageOffer(String amount) {
        Offer offer = new Offer();
        offer.setAmount(new BigDecimal(amount));
        return offer;
    }
}
