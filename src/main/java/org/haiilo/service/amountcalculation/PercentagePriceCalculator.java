package org.haiilo.service.amountcalculation;

import org.haiilo.domainobject.Offer;
import org.haiilo.serviceobject.CartItemSO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentagePriceCalculator implements PriceCalculator {
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    @Override
    public BigDecimal calculate(CartItemSO cartItem, Offer offer) {
        return cartItem.product().getPriceAmount()
                .multiply(BigDecimal.valueOf(cartItem.purchasedAmount()))
                .multiply(ONE_HUNDRED.subtract(offer.getAmount()))
                .divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
    }
}
