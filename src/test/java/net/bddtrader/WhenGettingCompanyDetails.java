package net.bddtrader;

import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenGettingCompanyDetails {

    @BeforeEach
    public void prepare_rest_config() {
        RestAssured.baseURI = "https://bddtrader.herokuapp.com/api";
    }

    @Test
    public void should_return_news_for_a_requested_company() {
        given().queryParam("symbols", "fb")
                .when()
                .get("/news")
                .then()
                .body("related", everyItem(containsString("FB")));
    }
}
