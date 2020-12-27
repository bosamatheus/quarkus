package com.bosamatheus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TimeResourceTest {

    @Test
    public void testTimeEndpoint() {
        given()
          .when().get("/time")
          .then()
             .statusCode(200);
    }
}
