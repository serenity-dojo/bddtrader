package net.bddtrader;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class JSONPathAssertionExerciseSolutions {


    @BeforeEach
    public void prepare_rest_config() {
        RestAssured.baseURI = "https://bddtrader.herokuapp.com/api";
    }

    /**
     * Exercise 1 - Read a single field value
     * Complete the test shown below to read and verify the "industry" field for AAPL
     * The result should be 'Telecommunications Equipment'
     */
    @Test
    public void find_a_simple_field_value() {
        given().pathParam("symbol", "aapl")
                .when().get("stock/{symbol}/company")
                .then().body("industry", equalTo("Telecommunications Equipment"));
    }

    /**
     * Exercise 2 - Read a single field value
     * Complete the test shown below to read and verify the "description" field for AAPL
     * The result should contain the string 'smartphones'
     */
    @Test
    public void check_that_a_field_value_contains_a_given_string() {

        given().pathParam("symbol", "aapl")
                .when().get("stock/{symbol}/company")
                .then().body("description", containsString("smartphones"));
    }


    /**
     * Exercise 3 - Read a single nested field value
     * Read the 'symbol' field inside the 'quote' entry in the Apple stock book (https://bddtrader.herokuapp.com/api/stock/aapl/book)
     * The result should be 'AAPL'
     */
    @Test
    public void find_a_nested_field_value() {
        given().pathParam("symbol", "aapl")
                .when().get("stock/{symbol}/book")
                .then().body("quote.symbol", equalTo("AAPL"));
    }


    /**
     * Exercise 4 - Find a list of values
     * Find the list of symbols recently traded from https://bddtrader.herokuapp.com/api/tops/last and
     * check that the list contains "PTN", "PINE" and "TRS"
     */
    @Test
    public void find_a_list_of_values() {
        when().get("/tops/last")
                .then().body("symbol", hasItems("PTN", "PINE", "TRS"));
    }

    /**
     * Exercise 5 - check that at least one item in a list matches a certain condition
     * Check that there is at least one price that is greater than 100.
     */
    @Test
    public void make_sure_at_least_one_item_matches_a_given_condition() {
        when().get("/tops/last")
                .then().body("price", hasItems(greaterThan(100.0f)));
    }

    /**
     * Exercise 6 - check the value of a specific item in a list
     * Check that price of the first trade in the Apple stock book is 319.59
     */
    @Test
    public void find_a_field_of_an_element_in_a_list() {
        given().pathParam("symbol","aapl")
                .when().get("/stock/{symbol}/book")
                .then().body("trades[0].price", equalTo(319.59f));
    }

    /**
     * Exercise 7 - check the value of a specific item in a list
     * Check that price of the first trade in the Apple stock book is 319.54
     */
    @Test
    public void find_a_field_of_the_last_element_in_a_list() {
        given().pathParam("symbol","aapl")
                .when().get("/stock/{symbol}/book")
                .then().body("trades[-1].price", equalTo(319.54f));
    }

    /**
     * Exercise 8 - check for number of elements in a collection
     * Check that there are 20 trades
     */
    @Test
    public void find_the_number_of_trades() {
        given().pathParam("symbol","aapl")
                .when().get("/stock/{symbol}/book")
                .then().body("trades.size()",equalTo(20));
    }

    /**
     * Exercise 9 - check for minimum or maximum
     * Check that the minimum price of any trade in the Apple stock book is 319.38
     */
    @Test
    public void find_the_minimum_trade_price() {
        given().pathParam("symbol","aapl")
                .when().get("/stock/{symbol}/book")
                .then().body("trades.price.min()", equalTo(319.38f));

    }

    /**
     * Exercise 10 - find a value using closure expressions
     * Check that the volume of the trade with the minimum price is 100
     */
    @Test
    public void find_the_size_of_the_trade_with_the_minimum_trade_price() {
        given().pathParam("symbol","aapl")
                .when().get("/stock/{symbol}/book")
                .then().body("trades.min { it.price }.volume", equalTo(100f));
    }

    /**
     * Exercise 11 - find a collection of objects matching a specified criteria
     * Check that there are 13 trades with prices greater than 319.50
     */
    @Test
    public void find_the_number_of_trades_with_a_price_greater_than_some_value() {
        RestAssured.given().pathParam("symbol","aapl")
                .when().get("/stock/{symbol}/book")
                .then().body("trades.findAll { it.price > 319.50 }.size()", equalTo(13));
    }

}
