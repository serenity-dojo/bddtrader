package net.bddtrader;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class AuthenticatedRequest {
    public static RequestSpecification withBasicAuthentication() {
        return RestAssured.given().auth().basic("user", "password");
    }
    public static RequestSpecification withDigestAuthentication() {
        return RestAssured.given().auth().digest("user", "password");
    }
}
