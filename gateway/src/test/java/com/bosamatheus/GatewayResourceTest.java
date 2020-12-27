package com.bosamatheus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GatewayResourceTest {

    @Test
    public void testGatewayEndpoint() {
        given()
          .when().get("/gateway")
          .then()
             .statusCode(200)
             .body(is("Gateway"));
    }
}
