package net.bddtrader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.clients.Client;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenCreatingANewClient {

    @BeforeEach
    public void setupBaseUrl() {
        RestAssured.baseURI = "http://localhost:9000/api";
    }

    @Test
    public void each_new_client_should_get_a_unique_id() {

        Map<String,Object> clientData = new HashMap<>();
        clientData.put("email","michael@scott.com");
        clientData.put("firstName","Michael");
        clientData.put("lastName","Scott");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(clientData)
                .when()
                .post("/client")
                .then().statusCode(200)
                .and().body("id", not(equalTo(0)))
                .and().body("email", equalTo("michael@scott.com"))
                .and().body("firstName", equalTo("Michael"))
                .and().body("lastName", equalTo("Scott"));
    }

    @Test
    public void a_new_client_can_be_created_using_a_map_structure() {

        Map<String,Object> clientData = new HashMap<>();
        clientData.put("email","kevin@malone.com");
        clientData.put("firstName","Kevin");
        clientData.put("lastName","Malone");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(clientData)
                .when()
                .post("/client")
                .then().statusCode(200)
                .and().body("id", not(equalTo(0)))
                .and().body("email", equalTo("kevin@malone.com"))
                .and().body("firstName", equalTo("Kevin"))
                .and().body("lastName", equalTo("Malone"));
    }

}
