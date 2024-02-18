package net.bddtrader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.clients.Client;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenUpdatingAndDeletingAClient {
    @BeforeEach
    public void setupBaseUrl() {
        RestAssured.baseURI = "http://localhost:9000/api";
    }

    @Test
    public void should_be_able_to_delete_a_client() {
        // Given a client exists
        String id = aClientExists(Client.withFirstName("Pam").andLastName("Beasley").andEmail("pam@beasly.com"));

        // When I delete the client
        AuthenticatedRequest.withDigestAuthentication()
                .delete("/client/{id}", id);

        // Then the client should no longer exist
        AuthenticatedRequest.withDigestAuthentication()
                .get("/client/{id}", id)
                .then()
                .statusCode(404);
    }

    @Test
    public void should_be_able_to_update_a_client() {
        Client pam = Client.withFirstName("Pam").andLastName("Beasley").andEmail("pam@beasly.com");
        // Given a client exists
        String id = aClientExists(pam);

        // When I update the email address of a client

        Client pamWithUpdates = Client.withFirstName("Pam").andLastName("Beasley").andEmail("pam@gmail.com");

        AuthenticatedRequest.withDigestAuthentication()
                .contentType(ContentType.JSON)
                .and().body(pamWithUpdates)
                .when().put("/client/{id}", id)
                .then().statusCode(200);

        AuthenticatedRequest.withDigestAuthentication()
                .when().get("/client/{id}", id)
                .then().body("email", equalTo("pam@gmail.com"));
    }

    private String aClientExists(Client existingClient) {
        return AuthenticatedRequest.withDigestAuthentication()
                .contentType(ContentType.JSON)
                .body(existingClient)
                .when()
                .post("/client")
                .jsonPath().getString("id");
    }
}
