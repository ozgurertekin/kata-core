package org.haiilo.service.amountcalculation;

import org.haiilo.domainobject.Offer;
import org.haiilo.domainvalue.DiscountType;

public class PriceFactory {

    public static PriceCalculator getPriceCalculator(Offer offer) {
        if(offer == null) {
            return new BasicPriceCalculator();
        }else if(offer.getDiscountType() == DiscountType.PERCENTAGE){
            return new PercentagePriceCalculator();
        }else if(offer.getDiscountType() == DiscountType.EXACT_PRICE){
            return new DiscountedPriceCalculator();
        }else {
            throw new IllegalArgumentException("Invalid discount type");
        }
    }
}
