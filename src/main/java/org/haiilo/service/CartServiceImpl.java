package org.haiilo.service;

import org.haiilo.domainobject.Offer;
import org.haiilo.service.amountcalculation.PriceCalculator;
import org.haiilo.service.amountcalculation.PriceFactory;
import org.haiilo.serviceobject.CartItemSO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private final OfferService offerService;

    public CartServiceImpl(OfferService offerService) {
        this.offerService = offerService;
    }


    @Override
    public BigDecimal calculateCartTotalAmount(List<CartItemSO> cartItemSOs) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItemSO cartItem : cartItemSOs) {
            Offer offer = offerService.findOfferByProduct(cartItem.product().getId());
            PriceCalculator priceCalculator = PriceFactory.getPriceCalculator(offer);
            totalAmount = totalAmount.add(priceCalculator.calculate(cartItem, offer));
        }
        return totalAmount;
    }
}
