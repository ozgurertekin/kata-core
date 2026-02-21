package org.haiilo.controller;

import org.haiilo.controller.mapper.CartMapper;
import org.haiilo.datatransferobject.CartItemDTO;
import org.haiilo.service.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/cart")
public class ShoppingCartController {

    private final CartService cartService;

    public ShoppingCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/calculate")
    public BigDecimal calculate(@RequestBody List<CartItemDTO> cartItemDTOs) {
        return cartService.calculateCartTotalAmount(CartMapper.makeCartSOs(cartItemDTOs));
    }
}
