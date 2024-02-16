package net.bddtrader;

import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenGettingCompanyDetails {

    @Test
    public void should_return_name_and_sector_in_production() {
        RestAssured.baseURI = "https://bddtrader.herokuapp.com/api";

        RestAssured.given()
                .pathParam("symbol","aapl")
                .when()
                .get("/stock/{symbol}/company")
                .then()
                .body("companyName", Matchers.equalTo("Apple, Inc."))
                .body("sector",Matchers.equalTo("Electronic Technology"));
    }

    @Test
    public void should_return_name_and_sector_locally() {
        RestAssured.baseURI = "http://localhost:9000/api";

        RestAssured.given()
                .pathParam("symbol","aapl")
                .when()
                .get("/stock/{symbol}/company")
                .then()
                .body("companyName", Matchers.equalTo("Apple, Inc."))
                .body("sector",Matchers.equalTo("Electronic Technology"));
    }
}
