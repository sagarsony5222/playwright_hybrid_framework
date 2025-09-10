package tests.api;

import base.BaseAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtil;

public class APITest extends BaseAPI {

    @Test(description = "GET /users returns 200 and non-empty list")
    public void getUsers_returnsSuccess() {
        Response res = APIUtil.get("/users");
        Assert.assertEquals(res.statusCode(), 200, "Expected 200 status");
        Assert.assertTrue(res.jsonPath().getList("$").size() > 0, "Expected non-empty users list");
    }

    @Test(description = "POST /login returns token for valid credentials")
    public void postLogin_returnsToken() {
        var body = new java.util.HashMap<String, Object>();
        body.put("email", config.getProperty("validUser"));
        body.put("password", config.getProperty("validPass"));

        Response res = APIUtil.post("/login", body);
        Assert.assertEquals(res.statusCode(), 200, "Expected 200 status");
        Assert.assertNotNull(res.jsonPath().getString("token"), "Expected auth token in response");
    }
}
