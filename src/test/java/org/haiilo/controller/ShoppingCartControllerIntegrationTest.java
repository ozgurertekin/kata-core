package org.haiilo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.sql.init.mode=never")
@AutoConfigureMockMvc
@Sql("/data.sql")
class ShoppingCartControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculate_shouldReturnTotalAmountUsingOffersAndBasicPrice() throws Exception {
        String requestBody = """
                [
                  {
                    "productDTO": {
                      "id": 1,
                      "name": "Laptop Stand",
                      "priceAmount": 29.90
                    },
                    "purchasedAmount": 2
                  },
                  {
                    "productDTO": {
                      "id": 3,
                      "name": "Mechanical Keyboard",
                      "priceAmount": 89.50
                    },
                    "purchasedAmount": 1
                  },
                  {
                    "productDTO": {
                      "id": 2,
                      "name": "Wireless Mouse",
                      "priceAmount": 19.99
                    },
                    "purchasedAmount": 1
                  }
                ]
                """;

        mockMvc.perform(post("/v1/cart/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string("133.80"));
    }
}
