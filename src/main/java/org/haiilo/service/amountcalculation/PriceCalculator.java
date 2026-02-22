package org.haiilo.service.amountcalculation;

import org.haiilo.domainobject.Offer;
import org.haiilo.serviceobject.CartItemSO;

import java.math.BigDecimal;

public interface PriceCalculator {
    BigDecimal calculate(CartItemSO  cartItem, Offer offer);
}
