package com.bastienvh.eurder;

import com.bastienvh.eurder.domain.item.Item;
import com.bastienvh.eurder.domain.order.CreateOrderDTO;
import com.bastienvh.eurder.domain.order.ItemGroup;
import com.bastienvh.eurder.domain.order.OrderItem;
import com.bastienvh.eurder.domain.Price;
import com.bastienvh.eurder.domain.customer.Address;
import com.bastienvh.eurder.domain.customer.CreateCustomerDTO;
import com.bastienvh.eurder.domain.customer.CustomerDTO;
import com.bastienvh.eurder.domain.item.Currency;
import com.bastienvh.eurder.domain.item.ItemDTO;
import com.bastienvh.eurder.repository.ItemRepository;
import com.bastienvh.eurder.repository.OrderRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AcceptanceTest {
    @LocalServerPort
    int port;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void whenAnOrderIsPlaced_thenOrderedAmountIsRemovedFromStock() {
        //GIVEN
        CustomerDTO customerDTO = createNewCustomer();
        ItemDTO itemDTO = new ItemDTO(0, "something", "something", new Price(BigDecimal.valueOf(5), Currency.EURO), 10);
        int itemId = addItemToRepo(itemDTO);
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerDTO.id(), List.of(
                new ItemGroup(itemId, 2)));
        //WHEN
        RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createOrderDTO)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //THEN
        Assertions.assertThat(itemRepository.getAmountInStockById(itemId)).isEqualTo(8);
    }

    @Test
    void whenAnOrderIsPlacedWithMultipleItems_thenOrderedAmountsAreRemovedFromStock() {
        //GIVEN
        CustomerDTO customerDTO = createNewCustomer();
        ItemDTO itemDTO1 = new ItemDTO(0, "something", "something", new Price(BigDecimal.valueOf(5), Currency.EURO), 10);
        ItemDTO itemDTO2 = new ItemDTO(1, "something else", "something else", new Price(BigDecimal.valueOf(1.5), Currency.EURO), 10);
        ItemDTO itemDTO3 = new ItemDTO(2, "something different", "something completely different", new Price(BigDecimal.valueOf(17.99), Currency.EURO), 10);
        int itemId1 = addItemToRepo(itemDTO1);
        int itemId2 = addItemToRepo(itemDTO2);
        int itemId3 = addItemToRepo(itemDTO3);
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerDTO.id(), List.of(
                new ItemGroup(itemId1, 2),
                new ItemGroup(itemId2, 7),
                new ItemGroup(itemId3, 9)));
        //WHEN
        RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createOrderDTO)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //THEN
        Assertions.assertThat(itemRepository.getAmountInStockById(itemId1)).isEqualTo(8);
        Assertions.assertThat(itemRepository.getAmountInStockById(itemId2)).isEqualTo(3);
        Assertions.assertThat(itemRepository.getAmountInStockById(itemId3)).isEqualTo(1);
    }

    @Test
    void getCorrectDeliveryDate_whenItemIsInStock_thenDeliveryDateIsTomorrow() {
        //GIVEN
        CustomerDTO customerDTO = createNewCustomer();
        ItemDTO itemDTO = new ItemDTO(0, "something", "something", new Price(BigDecimal.valueOf(5), Currency.EURO), 10);
        addItemToRepo(itemDTO);
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerDTO.id(), List.of(
                new ItemGroup(0, 2)));
        //WHEN
        RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createOrderDTO)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        OrderItem orderedItem = orderRepository.getOrderById(0).getOrderItemList().get(0);

        //THEN
        Assertions.assertThat(orderedItem.deliveryDate()).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void getCorrectDeliveryDate_whenItemIsNotInStock_thenDeliveryDateIsNextWeek() {
        CustomerDTO customerDTO = createNewCustomer();
        //GIVEN
        ItemDTO itemDTO = new ItemDTO(0, "something", "something", new Price(BigDecimal.valueOf(5), Currency.EURO), 10);
        int itemId = addItemToRepo(itemDTO);
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerDTO.id(), List.of(
                new ItemGroup(itemId, 12)));
        //WHEN
        int orderId = RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createOrderDTO)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .jsonPath()
                .getInt("orderId");

        OrderItem orderedItem = orderRepository.getOrderById(orderId).getOrderItemList().get(0);

        //THEN
        Assertions.assertThat(orderedItem.deliveryDate()).isEqualTo(LocalDate.now().plusDays(8));
    }

    @Test
    void getTotalPrice_whenProvidingAListOfItemsToOrder() {
        //GIVEN
        CustomerDTO customerDTO = createNewCustomer();
        ItemDTO itemDTO1 = new ItemDTO(0, "something", "something", new Price(BigDecimal.valueOf(5), Currency.EURO), 10);
        ItemDTO itemDTO2 = new ItemDTO(1, "something else", "something else", new Price(BigDecimal.valueOf(1.5), Currency.EURO), 10);
        ItemDTO itemDTO3 = new ItemDTO(2, "something different", "something completely different", new Price(BigDecimal.valueOf(17.99), Currency.EURO), 10);
        int itemId1 = addItemToRepo(itemDTO1);
        int itemId2 = addItemToRepo(itemDTO2);
        int itemId3 = addItemToRepo(itemDTO3);
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerDTO.id(), List.of(
                new ItemGroup(itemId1, 2),
                new ItemGroup(itemId2, 3),
                new ItemGroup(itemId3, 5)));
        //WHEN
        Price totalPrice = RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createOrderDTO)
                .when()
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .jsonPath()
                .getObject("totalPrice", Price.class);

        //THEN
        Assertions.assertThat(totalPrice).isEqualTo(new Price(BigDecimal.valueOf(104.45), Currency.EURO));
    }

    private int addItemToRepo(ItemDTO itemDTO) {
        return RestAssured
                .given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(itemDTO)
                .post("/items")
                .then()
                .extract()
                .as(Integer.class);
    }

    private CustomerDTO createNewCustomer() {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(
                "firstname",
                "lastName",
                "first.last@example.com",
                new Address("street", "number", "city", 1000),
                "phone number");

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createCustomerDTO)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .extract()
                .as(CustomerDTO.class);
    }
}
