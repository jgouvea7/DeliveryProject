package com.project.delivery.courier.management.api.controller;

import com.project.delivery.courier.management.domain.model.Courier;
import com.project.delivery.courier.management.domain.repository.CourierRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourierControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CourierRepository courierRepository;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/couriers";
    }

    @Test
    public void shouldReturn201() {
        String requestBody = """
              {
                  "name": "Joao da Silva",
                  "phone": "11995578765"
              }
              """;

        RestAssured
                .given()
                    .body(requestBody)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("id", Matchers.notNullValue())
                    .body("name", Matchers.equalTo("Joao da Silva"));


    }

    @Test
    void shouldReturn200() {
        UUID courierId = courierRepository.saveAndFlush(
                Courier.brandNew(
                        "Jonnathas Gouvea",
                        "11963748832"
                )
        ).getId();

        RestAssured
                .given()
                    .pathParam("courierId", courierId)
                    .accept(ContentType.JSON)
                .when()
                    .get("/{courierId}")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", Matchers.equalTo(courierId.toString()))
                    .body("name", Matchers.equalTo("Jonnathas Gouvea"))
                    .body("phone", Matchers.equalTo("11963748832"));
    }

}