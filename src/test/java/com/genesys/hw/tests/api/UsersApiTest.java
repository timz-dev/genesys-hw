package com.genesys.hw.tests.api;

import com.genesys.hw.models.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

// ponytail: no BaseTest, API tests don't need a browser
public class UsersApiTest {
    private static final Logger logger = LogManager.getLogger(UsersApiTest.class);
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Test
    public void rest_api_testing() {
        Response response = RestAssured.given().baseUri(BASE_URL).get("/users");
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code");
        Assert.assertTrue(response.getContentType().contains(ContentType.JSON.toString()), "Unexpected content type");

        JsonPath jsonPath = response.jsonPath();
        List<User> users = jsonPath.getList("$", User.class);

        for (User user : users) {
            logger.info(user.getName() + " | " + user.getEmail());
        }

        String firstEmail = jsonPath.getString("[0].email");
        Assert.assertTrue(firstEmail.contains("@"), "First email address doesn't contain @");
    }
}
