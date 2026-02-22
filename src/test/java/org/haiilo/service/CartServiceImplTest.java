package org.haiilo.service;

import org.haiilo.config.TestClock;
import org.haiilo.domainobject.Offer;
import org.haiilo.domainobject.Product;
import org.haiilo.domainvalue.DiscountType;
import org.junit.jupiter.api.BeforeEach;
import org.haiilo.serviceobject.CartItemSO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
    private static final String EUROPE_BERLIN = "Europe/Berlin";
    private static final LocalDateTime CAMPAIGN_START = LocalDateTime.of(2026, 2, 1, 0, 0);
    private static final LocalDateTime CAMPAIGN_END = LocalDateTime.of(2026, 3, 1, 0, 0);

    @Mock
    private OfferService offerService;

    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        Clock fixedClock = TestClock.fixedUtcClock();
        cartService = new CartServiceImpl(offerService, fixedClock);
    }

    @Test
    void calculateCartTotalAmount_shouldSumBasicPercentageAndDiscountedAmounts() {
        CartItemSO basicItem = cartItem(1L, "Laptop Stand", "10.00", 2);
        CartItemSO percentageItem = cartItem(2L, "Wireless Mouse", "50.00", 1);
        CartItemSO discountedItem = cartItem(3L, "Keyboard", "100.00", 1);

        Offer percentageOffer = offer(DiscountType.PERCENTAGE, "10.00");
        Offer discountedOffer = offer(DiscountType.EXACT_PRICE, "80.00");

        when(offerService.findOfferByProduct(1L)).thenReturn(null);
        when(offerService.findOfferByProduct(2L)).thenReturn(percentageOffer);
        when(offerService.findOfferByProduct(3L)).thenReturn(discountedOffer);

        BigDecimal result = cartService.calculateCartTotalAmount(List.of(basicItem, percentageItem, discountedItem));

        assertThat(result).isEqualByComparingTo("145.00");
        verify(offerService).findOfferByProduct(1L);
        verify(offerService).findOfferByProduct(2L);
        verify(offerService).findOfferByProduct(3L);
    }

    @Test
    void calculateCartTotalAmount_shouldReturnZeroForEmptyCart() {
        BigDecimal result = cartService.calculateCartTotalAmount(List.of());

        assertThat(result).isEqualByComparingTo("0");
        verifyNoInteractions(offerService);
    }

    private static CartItemSO cartItem(Long id, String name, String priceAmount, int purchasedAmount) {
        return new CartItemSO(new Product(id, name, new BigDecimal(priceAmount)), purchasedAmount);
    }

    private static Offer offer(DiscountType discountType, String amount) {
        Offer offer = new Offer();
        offer.setDiscountType(discountType);
        offer.setAmount(new BigDecimal(amount));
        offer.setTimeZone(EUROPE_BERLIN);
        offer.setCampaignStart(CAMPAIGN_START);
        offer.setCampaignEnd(CAMPAIGN_END);
        return offer;
    }
}
