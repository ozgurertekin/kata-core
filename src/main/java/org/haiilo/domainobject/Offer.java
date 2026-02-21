package org.haiilo.domainobject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import org.haiilo.domainvalue.DiscountType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @NotNull
    private Product product;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime campaignStart;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime campaignEnd;

    @Column(name = "time_zone", nullable = false, length = 64)
    private String timeZone; // e.g. "Europe/Berlin"

    @NotNull
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Column(nullable = false, precision = 19, scale = 2)
    @NotNull
    @Digits(integer = 17, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal amount;

    public Offer(Long id, Product product, LocalDateTime campaignStart, LocalDateTime campaignEnd, String timeZone, DiscountType discountType, BigDecimal amount) {
        this.id = id;
        this.product = product;
        this.campaignStart = campaignStart;
        this.campaignEnd = campaignEnd;
        this.timeZone = timeZone;
        this.discountType = discountType;
        this.amount = amount;
    }

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getCampaignStart() {
        return campaignStart;
    }

    public void setCampaignStart(LocalDateTime campaignStart) {
        this.campaignStart = campaignStart;
    }

    public LocalDateTime getCampaignEnd() {
        return campaignEnd;
    }

    public void setCampaignEnd(LocalDateTime campaignEnd) {
        this.campaignEnd = campaignEnd;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
