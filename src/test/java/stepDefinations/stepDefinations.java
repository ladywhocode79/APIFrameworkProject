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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import util.ReusableMethods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class stepDefinations extends Utils {
    RequestSpecification res;
    ResponseSpecification responseSpecification;
   Response response;
   TestDataBuild dataBuild = new TestDataBuild();
    static String place_id;

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

    @When("User calls {string} with {string} Http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
       APIResources apiResources = APIResources.valueOf(resource);

        responseSpecification =new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).build();
        String method = httpMethod.toLowerCase();
        switch (method)
        {
            case "post":
                response =  res.when(). post(apiResources.getResource());
                break;
            case "get":
                response =  res.when(). get(apiResources.getResource());
                break;
            default:
            case "delete":
                response =  res.when(). delete(apiResources.getResource());
                break;

        }

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
      /*  String stringResponse = response.asString();
        jsonPath = ReusableMethods.rawToJson(stringResponse);*/

       Assert.assertEquals(value,getJsonPath(response,keyValue));


    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String name, String resourceName) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        //get place_id
         place_id =getJsonPath(response,"place_id");
        res =given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resourceName,"GET");
        Assert.assertEquals(name,getJsonPath(response,"name"));

    }
    @Given("DeletePlace payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res  = given().spec(requestSpecification()).body(dataBuild.deletePlace(place_id));
    }
}
