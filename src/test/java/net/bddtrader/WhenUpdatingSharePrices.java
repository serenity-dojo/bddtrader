package net.bddtrader;

import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.SerenityRest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.when;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenUpdatingSharePrices {

    @BeforeEach
    public void setBaseURL() {
        RestAssured.baseURI = "http://localhost:9000/api";
    }

    @Test
    public void should_be_able_to_update_a_share_price_in_the_dev_environment() {

        AuthenticatedRequest.withDigestAuthentication()
                .when().given().contentType("application/json")
                .and().body("499.99")
                .and().pathParam("stock","aapl")
                .and().post("/stock/{stock}/price");

        AuthenticatedRequest.withDigestAuthentication()
                .when().get("/stock/{stock}/price", "aapl")
                .then().statusCode(200);

        String updatedPrice = SerenityRest.lastResponse().body().asString();

        assertThat(updatedPrice).isEqualTo("499.99");

    }
}