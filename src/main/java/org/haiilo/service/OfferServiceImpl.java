package org.haiilo.service;

import org.haiilo.dataaccessobject.OfferRepository;
import org.haiilo.domainobject.Offer;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer findOfferByProduct(Long productId) {
        return offerRepository.findOfferByProductId(productId);
    }
}
