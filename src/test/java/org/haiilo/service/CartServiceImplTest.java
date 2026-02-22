package org.haiilo.service;

import org.haiilo.domainobject.Offer;
import org.haiilo.domainobject.Product;
import org.haiilo.domainvalue.DiscountType;
import org.haiilo.serviceobject.CartItemSO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private OfferService offerService;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    void calculateCartTotalAmount_shouldSumBasicPercentageAndDiscountedAmounts() {
        CartItemSO basicItem = new CartItemSO(new Product(1L, "Laptop Stand", new BigDecimal("10.00")), 2);
        CartItemSO percentageItem = new CartItemSO(new Product(2L, "Wireless Mouse", new BigDecimal("50.00")), 1);
        CartItemSO discountedItem = new CartItemSO(new Product(3L, "Keyboard", new BigDecimal("100.00")), 1);

        Offer percentageOffer = new Offer();
        percentageOffer.setDiscountType(DiscountType.PERCENTAGE);
        percentageOffer.setAmount(new BigDecimal("10.00"));

        Offer discountedOffer = new Offer();
        discountedOffer.setDiscountType(DiscountType.EXACT_PRICE);
        discountedOffer.setAmount(new BigDecimal("80.00"));

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
}
