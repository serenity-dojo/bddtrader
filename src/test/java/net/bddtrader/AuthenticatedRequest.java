package net.bddtrader;


import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class AuthenticatedRequest {
    public static RequestSpecification withBasicAuthentication() {
        return SerenityRest.given().auth().basic("user", "password");
    }
    public static RequestSpecification withDigestAuthentication() {
        return SerenityRest.given().auth().digest("user", "password");
    }
}
