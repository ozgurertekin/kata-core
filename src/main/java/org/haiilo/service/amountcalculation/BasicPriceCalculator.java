package org.haiilo.service.amountcalculation;

import org.haiilo.domainobject.Offer;
import org.haiilo.serviceobject.CartItemSO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BasicPriceCalculator implements PriceCalculator {

    @Override
    public BigDecimal calculate(CartItemSO cartItem, Offer offer) {
        return cartItem.product().getPriceAmount()
                .multiply(BigDecimal.valueOf(cartItem.purchasedAmount()))
                .setScale(2, RoundingMode.HALF_UP) ;
    }
}
