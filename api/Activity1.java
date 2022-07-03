package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


import static io.restassured.RestAssured.given;

public class Activity1 {

    final static  String baseURL = "https://petstore.swagger.io/v2/pet";
    Response response;
    @Test(priority = 1)

    public void postRequest(){
        String inputBody = "{"
                + "\"id\": 77232,"
                + "\"name\": \"Riley\","
                + " \"status\": \"alive\""
                + "}";


        response = given().contentType(ContentType.JSON).when().body(inputBody).post(baseURL);
        int id=response.then().extract().body().path("id");
        String name = response.then().extract().body().path("name");
        String status = response.then().extract().body().path("status");

        Assert.assertEquals(id,77232);
        Assert.assertEquals(name,"Riley");
        Assert.assertEquals(status,"alive");


    }
  @Test(priority = 2)
    public  void getPet(){
        response = given().contentType(ContentType.JSON).pathParam("petId","77232").when().get(baseURL+"/{petId}");
    }

    @Test(priority = 3)
    public void deletePet(){
        response = given().contentType(ContentType.JSON).pathParam("petId","77232").when().delete(baseURL+"/{petId}");
        response.then().statusCode(200);
        String message = response.then().extract().path("message");
        Assert.assertEquals(message,"77232");

    }
}
