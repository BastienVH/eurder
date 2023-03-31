package com.bastienvh.eurder.integrationtests;

import com.bastienvh.eurder.domain.Price;
import com.bastienvh.eurder.domain.item.CreateItemDTO;
import com.bastienvh.eurder.domain.item.Currency;
import com.bastienvh.eurder.repository.ItemRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ItemIntegrationTest {
    @LocalServerPort
    int port;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void createItem_withValidCreateItemDTO_thenHttpStatusIs201AndIdIsReturned() {
        //GIVEN
        CreateItemDTO validItem = new CreateItemDTO("Twix bar", "A delicious candy bar", new Price(BigDecimal.valueOf(1.49), Currency.EURO), 42);

        //WHEN
        int itemId = RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(validItem)
                .when()
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(Integer.class);
        //THEN
        Assertions.assertThat(itemRepository.getAllItems().size()).isEqualTo(1);
    }


    //test making a new item with invalid price

    //test making a new item with invalid stock amount

    //test making a new item with invalid name or description

}
