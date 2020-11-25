package net.bddtrader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class WhenCreatingANewClient {

    @Before
    public void setupBaseUrl() {
        RestAssured.baseURI = "http://localhost:9000/api";
    }

    @Test
    public void each_new_client_should_get_a_unique_id() {

        String newClient = "{\n" +
                "  \"email\": \"michael@scott.com\",\n" +
                "  \"firstName\": \"Michael\",\n" +
                "  \"lastName\": \"Scott\"\n" +
                "}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(newClient)
                .when()
                .post("/client")
                .then().statusCode(200)
                .and().body("id", not(equalTo(0)))
                .and().body("email", equalTo("michael@scott.com"))
                .and().body("firstName", equalTo("Michael"))
                .and().body("lastName", equalTo("Scott"));
    }
}
