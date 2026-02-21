package org.haiilo.service;

import org.haiilo.serviceobject.CartItemSO;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
     BigDecimal calculateCartTotalAmount(List<CartItemSO> cartItemSOs);
}
