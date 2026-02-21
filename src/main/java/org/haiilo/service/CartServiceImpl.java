package org.haiilo.service;

import org.haiilo.serviceobject.CartItemSO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Override
    public BigDecimal calculateCartTotalAmount(List<CartItemSO> cartItemSOs) {

        return null;
    }
}
