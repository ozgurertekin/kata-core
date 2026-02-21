package org.haiilo.controller.mapper;

import org.haiilo.datatransferobject.CartItemDTO;
import org.haiilo.serviceobject.CartItemSO;

import java.util.List;

public class CartMapper {
    public static CartItemSO makeCardSO(CartItemDTO cartItemDTO) {
        return new CartItemSO(ProductMapper.makeProduct(cartItemDTO.productDTO()), cartItemDTO.purchasedAmount());
    }

    public static List<CartItemSO> makeCartSOs(List<CartItemDTO> cartItemSOs) {
        return cartItemSOs.stream()
                .map(CartMapper::makeCardSO)
                .toList();
    }

}
