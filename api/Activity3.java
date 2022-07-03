package activities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity3 {

    RequestSpecification requestSpecification ;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void  setUp(){
        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://petstore.swagger.io/v2/pet").
                setContentType(ContentType.JSON).build();
        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                expectBody("status",equalTo("alive")).
               build();
       }

    @Test(priority = 1)
    public void postRequest(){
        String inputBody = "{\"id\": 77232, \"name\": \"Riley\", \"status\": \"alive\"}";
        Response response = given().spec(requestSpecification).body(inputBody).when().post();
        inputBody = "{\"id\": 77233, \"name\": \"Hansel\", \"status\": \"alive\"}";
        response = given().spec(requestSpecification).body(inputBody).when().post();
        response.then().spec(responseSpecification);

    }

    @Test(priority = 2,dataProvider = "petDetails")
    public void getRequest(int id,String name ,String status){
        Response response = given().spec(requestSpecification).pathParam("petId",id).when().get("/{petId}");
        response.then().spec(responseSpecification);
        response.then().body("name",equalTo(name));

    }

    @Test(priority = 3,dataProvider = "petDetails")
    public void deleteRequest(int id,String name,String status){
        Response response = given().spec(requestSpecification).pathParam("petId",id).when().delete("/{petId}");
        response.then().statusCode(200);
    }

    @DataProvider
    public  Object[][] petDetails(){
        Object[][] testData = new Object[][] {
                { 77232, "Riley", "alive" },
                { 77233, "Hansel", "alive" }
        };
        return  testData;
    }
}
