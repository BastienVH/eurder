package com.bastienvh.eurder.integrationtests;

import com.bastienvh.eurder.domain.customer.Address;
import com.bastienvh.eurder.domain.customer.CreateCustomerDTO;
import com.bastienvh.eurder.domain.customer.CustomerDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerIntegrationTest {
    @LocalServerPort
    int port;


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

    @Test
    void createCustomer_withIncompleteFields_returnsExceptionMessage() {
        //GIVEN
        CreateCustomerDTO createCustomerDTOWithoutFirstName = new CreateCustomerDTO(
                null,
                "lastName",
                "first.last@example.com",
                new Address("street", "number", "city", 1000),
                "phone number");

        //WHEN
        String[] response = RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createCustomerDTOWithoutFirstName)
                .when()
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .extract()
                .as(String[].class);

        //THEN
        Assertions.assertThat(response[0]).isEqualTo("first name is mandatory");
    }

    @Test
    void createCustomer_withInvalidAddress_returnsExceptionMessage() {
        //GIVEN
        CreateCustomerDTO createCustomerDTOWithoutFirstName = new CreateCustomerDTO(
                "firstName",
                "lastName",
                "first.last@example.com",
                new Address(null, "number", "city", 1000),
                "phone number");

        //WHEN
        String[] response = RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createCustomerDTOWithoutFirstName)
                .when()
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .extract()
                .as(String[].class);

        //THEN
        Assertions.assertThat(response[0]).isEqualTo("street name not provided");
    }

    @Test
    void createCustomer_withoutPostalCode_returnsExceptionMessage() {
        //GIVEN
        CreateCustomerDTO createCustomerDTOWithoutFirstName = new CreateCustomerDTO(
                "firstName",
                "lastName",
                "first.last@example.com",
                new Address("Kantersteen", "number", "city", 0),
                "phone number");

        //WHEN
        String[] response = RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createCustomerDTOWithoutFirstName)
                .when()
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .extract()
                .as(String[].class);

        //THEN
        Assertions.assertThat(response[0]).isEqualTo("postal code not provided or below minimum threshold (1000)");
    }

    @Test
    void createCustomer_withInvalidPostalCode_returnsExceptionMessage() {
        //GIVEN
        CreateCustomerDTO createCustomerDTOWithoutFirstName = new CreateCustomerDTO(
                "firstName",
                "lastName",
                "first.last@example.com",
                new Address("Kantersteen", "number", "city", 10200),
                "phone number");

        //WHEN
        String[] response = RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createCustomerDTOWithoutFirstName)
                .when()
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .extract()
                .as(String[].class);

        //THEN
        Assertions.assertThat(response[0]).isEqualTo("postal code is above maximum threshold (9999)");
    }
}
