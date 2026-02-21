package org.haiilo.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CartItemDTO
        (ProductDTO productDTO,

         Integer purchasedAmount) {
}
