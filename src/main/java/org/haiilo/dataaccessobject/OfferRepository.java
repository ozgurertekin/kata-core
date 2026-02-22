package org.haiilo.dataaccessobject;

import org.haiilo.domainobject.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findOfferByProductId(Long productId);
}
