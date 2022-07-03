package livePackage;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GitHub_RestAssured_Project {

    RequestSpecification requestSpecification;
    String sshKey;
    int id =0;
    String baseUri ="https://api.github.com";

    @BeforeClass
    public void setUp(){
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://api.github.com")
                .addHeader("Authorization","token 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").build();
    }
    @Test(priority = 1)
    public void postRequest(){
        String responseBody = "{\n" +
                "    \"title\": \"TestAPIKey\",\n" +
                "    \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCP5uZCot1cDisiIrpnp2QQC/zbjliGDVG0Zeme58HPeyvr25BfclwEm0Y806XGW3NWI0cyhZ5Tv6ZkvjhrXoWjCE7zfSdHsX+8gg+R62Ugw7H5lJmN1sJ9zVZN3xUf8yXv0nDZY7lFyEYQDH26uC129uxag05yOAGodOd31YC0xeG1UMcEjNx8ix9MSRDBZC1X6QyPW4qwcYBcNboKHHN+TLkaFmNzAlitgErDhml1Vm7iWivcrsurtk/prdHrVoRkoaPTfaR/od4EdUltQylbEuNaR/MihqVpSTZI972epLCVenqsI7bY29tbn8o9KEIPmA2YxrNaTdcr+DEdRODN \" \n" +
                "}\n";
       Response response = given().spec(requestSpecification).
                body(responseBody).when().post("/user/keys");
        System.out.println("Response is --->" +response.getBody().asPrettyString());
       id = response.then().extract().body().path("id");
        response.then().statusCode(201);
    }

    @Test(priority = 2)
    public void getRequest(){
        Response response = given().spec(requestSpecification).pathParam("keyId",id)
               .when().get("/user/keys/{keyId}");
        response.then().log().body();
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    public void deleteRequest(){
         Response response = given().spec(requestSpecification).pathParam("keyId",id)
                .when().delete("/user/keys/{keyId}");
        response.then().log().body();
        response.then().statusCode(204);
    }

}
