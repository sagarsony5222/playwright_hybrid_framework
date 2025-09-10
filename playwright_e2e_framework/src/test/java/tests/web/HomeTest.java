package tests.web;

import base.BaseTest;
import com.microsoft.playwright.Page;
import drivers.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomeTest extends BaseTest {

    @Test(description = "Search feature returns results")
    public void search_returnsResults() {
        Page page = PlaywrightFactory.getPage();
        // Assuming logged-in state required; perform lightweight login flow
        HomePage home = new LoginPage(page)
                .navigate(config.getProperty("baseUrl"))
                .enterEmail(config.getProperty("validUser"))
                .enterPassword(config.getProperty("validPass"))
                .clickSignIn();

        home.search("Playwright");
        Assert.assertTrue(page.url().contains("search"), "Expected to be on search results page");
    }

    @Test(description = "User can logout from user menu")
    public void user_canLogout() {
        Page page = PlaywrightFactory.getPage();
        HomePage home = new LoginPage(page)
                .navigate(config.getProperty("baseUrl"))
                .enterEmail(config.getProperty("validUser"))
                .enterPassword(config.getProperty("validPass"))
                .clickSignIn();

        LoginPage login = home.logout();
        Assert.assertTrue(login.isLoaded(), "Expected login page after logout");
    }
}
