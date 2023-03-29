package com.bastienvh.eurder.integrationtests;

import com.bastienvh.eurder.domain.customer.Address;
import com.bastienvh.eurder.domain.customer.CreateCustomerDTO;
import com.bastienvh.eurder.domain.customer.CustomerDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerIntegrationTest {
    @LocalServerPort int port;

    @Test
    void getOneCustomer_withWrongUUID_thenThrowException() {
        //GIVEN
         Response response = given()
                 .baseUri("http://localhost")
                 .port(port)
                 .get("/customers/" + UUID.randomUUID());

        Assertions.assertThat(response.asString()).contains("No user found");
    }
    @Test
    void getOneCustomer_withCorrectUUID_thenReturnsCorrectCustomerDTO() {
        //GIVEN
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(
                "firstname",
                "lastName",
                "first.last@example.com",
                new Address("street", "number", "city", 1000),
                "phone number");

        CustomerDTO DTOfromCreation = given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .body(createCustomerDTO)
                .post("/customers")
                .then()
                .extract()
                .as(CustomerDTO.class);

        // get with response UUID
        CustomerDTO DTOFromGet = given()
                .baseUri("http://localhost")
                .port(port)
                .get("/customers/" + DTOfromCreation.id())
                .then()
                .extract()
                .as(CustomerDTO.class);
        Assertions.assertThat(DTOFromGet).isEqualTo(DTOfromCreation);
    }


}
