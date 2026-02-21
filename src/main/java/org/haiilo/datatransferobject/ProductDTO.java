package org.haiilo.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductDTO
    (
    Long id,

    @NotNull
    String name,

    @NotNull
    BigDecimal priceAmount
    )
    {}
