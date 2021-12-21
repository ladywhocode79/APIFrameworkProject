package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojoclass.AddPlace;
import pojoclass.Location;
import resources.TestDataBuild;
import resources.Utils;
import util.ReusableMethods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class stepDefinations extends Utils {
    RequestSpecification res;
    ResponseSpecification responseSpecification;
   Response response;
   TestDataBuild dataBuild = new TestDataBuild();
   /* @Given("Add Place Payload")
    public void add_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        *//* responseSpecification =new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).build();*//*

         res =given().spec(requestSpecification()).
                body(dataBuild.addPlacePayload());
    }*/
   @Given("Add Place Payload with {string} {string} {string}")
   public void add_place_payload_with(String name, String language, String address) throws IOException {
       // Write code here that turns the phrase above into concrete actions
       res =given().spec(requestSpecification()).
               body(dataBuild.addPlacePayload(name,language,address));
   }

    @When("User calls {string} with Post Http request")
    public void user_calls_with_post_http_request(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        responseSpecification =new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).build();
         response =
                res.
                        when().
                        post("/maps/api/place/add/json").
                        then().spec(responseSpecification).extract().response();

    }
    @Then("The API call is successful with Status code {int}")
    public void the_api_call_is_successful_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String value) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        String stringResponse = response.asString();
        JsonPath jsonPath = ReusableMethods.rawToJson(stringResponse);

       Assert.assertEquals(value,jsonPath.get(keyValue).toString());
    }
}
