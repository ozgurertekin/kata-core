package org.haiilo.service;

import org.haiilo.domainobject.Offer;
import org.haiilo.service.amountcalculation.PriceCalculator;
import org.haiilo.service.amountcalculation.PriceFactory;
import org.haiilo.serviceobject.CartItemSO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private final OfferService offerService;

    private final Clock clock;

    public CartServiceImpl(OfferService offerService, Clock clock) {
        this.offerService = offerService;
        this.clock = clock;
    }


    @Override
    public BigDecimal calculateCartTotalAmount(List<CartItemSO> cartItemSOs) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItemSO cartItem : cartItemSOs) {
            Offer offer = offerService.findOfferByProduct(cartItem.product().getId());
            PriceCalculator priceCalculator = PriceFactory.getPriceCalculator(isActiveNow(offer, clock) ? offer: null);
            totalAmount = totalAmount.add(priceCalculator.calculate(cartItem, offer));
        }
        return totalAmount;
    }

    private boolean isActiveNow(Offer offer, Clock clock) {
        if(offer == null) {
            return false;
        }

        ZoneId zone = ZoneId.of(offer.getTimeZone());

        // current wall-clock time in that zone
        LocalDateTime nowLocal = LocalDateTime.ofInstant(clock.instant(), zone);

        return !nowLocal.isBefore(offer.getCampaignStart())
                &&  nowLocal.isBefore(offer.getCampaignEnd());
    }
}
