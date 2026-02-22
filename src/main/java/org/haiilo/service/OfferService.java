package org.haiilo.service;

import org.haiilo.domainobject.Offer;

public interface OfferService {
    Offer findOfferByProduct(Long productId);
}
