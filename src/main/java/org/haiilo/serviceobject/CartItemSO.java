package org.haiilo.serviceobject;

import org.haiilo.domainobject.Product;


public record CartItemSO
        (Product product,

         Integer purchasedAmount) {
}