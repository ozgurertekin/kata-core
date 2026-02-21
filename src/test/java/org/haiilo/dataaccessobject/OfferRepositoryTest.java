package org.haiilo.dataaccessobject;

import org.haiilo.domainobject.Offer;
import org.haiilo.domainvalue.DiscountType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@DataJpaTest(properties = "spring.sql.init.mode=never")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql("/data.sql")
class OfferRepositoryTest {

    @Autowired
    private OfferRepository offerRepository;

    @Test
    void findAll_shouldReturnAllOffers() {
        List<Offer> offers = offerRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Offer::getId))
                .toList();

        assertThat(offers).hasSize(3);
        assertThat(offers)
                .extracting(
                        Offer::getId,
                        offer -> offer.getProduct().getId(),
                        offer -> offer.getCampaignStart().atZone(ZoneId.of(offer.getTimeZone())).toInstant(),
                        offer -> offer.getCampaignEnd().atZone(ZoneId.of(offer.getTimeZone())).toInstant(),
                        Offer::getTimeZone,
                        Offer::getDiscountType,
                        Offer::getAmount
                )
                .containsExactly(
                        tuple(
                                1L,
                                1L,
                                Instant.parse("2026-02-01T00:00:00Z"),
                                Instant.parse("2026-03-01T00:00:00Z"),
                                "Europe/Berlin",
                                DiscountType.PERCENTAGE,
                                new BigDecimal("10.00")
                        ),
                        tuple(
                                2L,
                                3L,
                                Instant.parse("2026-02-10T00:00:00Z"),
                                Instant.parse("2026-03-10T00:00:00Z"),
                                "Europe/Berlin",
                                DiscountType.EXACT_PRICE,
                                new BigDecimal("59.99")
                        ),
                        tuple(
                                3L,
                                5L,
                                Instant.parse("2026-02-15T00:00:00Z"),
                                Instant.parse("2026-04-15T00:00:00Z"),
                                "Europe/Berlin",
                                DiscountType.PERCENTAGE,
                                new BigDecimal("15.50")
                        )
                );
    }
}
