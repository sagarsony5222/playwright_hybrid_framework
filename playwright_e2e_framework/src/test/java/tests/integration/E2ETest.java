package tests.integration;

import base.BaseTest;
import com.microsoft.playwright.Page;
import drivers.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.APIUtil;
import io.restassured.response.Response;

public class E2ETest extends BaseTest {

    @Test(description = "End-to-end: create data via API, verify via UI, clean up")
    public void e2e_createAndVerify() {
        // 1) Arrange via API
        var payload = new java.util.HashMap<String, Object>();
        payload.put("name", "Playwright Item");
        payload.put("type", "test");

        Response createRes = APIUtil.post("/items", payload);
        Assert.assertEquals(createRes.statusCode(), 201, "Expected 201 on create");
        String itemId = createRes.jsonPath().getString("id");

        // 2) Act via UI
        Page page = PlaywrightFactory.getPage();
        HomePage home = new LoginPage(page)
                .navigate(config.getProperty("baseUrl"))
                .enterEmail(config.getProperty("validUser"))
                .enterPassword(config.getProperty("validPass"))
                .clickSignIn();

        home.search("Playwright Item");
        Assert.assertTrue(page.content().contains("Playwright Item"), "Expected item to appear in UI");

        // 3) Cleanup via API
        Response delRes = APIUtil.delete("/items/" + itemId);
        Assert.assertEquals(delRes.statusCode(), 204, "Expected 204 on delete");
    }
}
