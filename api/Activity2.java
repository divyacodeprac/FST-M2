package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity2 {

    final static  String baseURL = "https://petstore.swagger.io/v2/user";
    Response response;
    @Test(priority = 1)

    public void addUser(){
        String inputBody = "{\n" +
                "  \"id\": 9902,\n" +
                "  \"username\": \"mark\",\n" +
                "  \"firstName\": \"mark\",\n" +
                "  \"lastName\": \"matthew\",\n" +
                "  \"email\": \"markmike@mail.com\",\n" +
                "  \"password\": \"password123\",\n" +
                "  \"phone\": \"9812763452\"\n" +
                "}";


        response = given().contentType(ContentType.JSON).when().body(inputBody).post(baseURL);
        response.then().statusCode(200);
        response.then().body("message",equalTo("9902"));
        System.out.println("Response is--->"+response.getBody().asString());

    }
  @Test(priority = 2)
    public  void getUser(){
      File outputJSON = new File("src/main/java/activities/userGETResponse.json");

        response = given().contentType(ContentType.JSON).pathParam("username","mark").when().get(baseURL+"/{username}");
      String resBody = response.getBody().asPrettyString();
        try {
          // Create JSON file
          outputJSON.createNewFile();
          // Write response body to external file
          FileWriter writer = new FileWriter(outputJSON.getPath());
          writer.write(resBody);
          writer.close();

      } catch (IOException excp) {

          excp.printStackTrace();

      }
      response.then().body("id", equalTo(9902));
      response.then().body("username", equalTo("mark"));
      response.then().body("firstName", equalTo("mark"));
      response.then().body("lastName", equalTo("matthew"));

    }

    @Test(priority = 3,enabled = false)
    public void deletePet(){
        response = given().contentType(ContentType.JSON).pathParam("username","mark").when().delete(baseURL+"/{username}");
        response.then().statusCode(200);
        String message = response.then().extract().path("message");
        Assert.assertEquals(message,"mark");

    }
}
