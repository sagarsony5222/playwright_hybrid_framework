package tests.web;

import base.BaseTest;
import com.microsoft.playwright.Page;
import drivers.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "Valid user can log in and reach home page")
    public void validLogin_navigatesToHome() {
        Page page = PlaywrightFactory.getPage();
        LoginPage login = new LoginPage(page).navigate(config.getProperty("baseUrl"));
        HomePage home = login.enterEmail(config.getProperty("validUser"))
                .enterPassword(config.getProperty("validPass"))
                .clickSignIn();
        Assert.assertTrue(home.isLoggedIn(), "Expected user to be logged in");
    }

    @Test(description = "Invalid login shows error message")
    public void invalidLogin_showsError() {
        Page page = PlaywrightFactory.getPage();
        LoginPage login = new LoginPage(page).navigate(config.getProperty("baseUrl"));
        login.enterEmail("bad@example.com")
                .enterPassword("wrongpass")
                .clickSignIn();
        Assert.assertTrue(login.getErrorMessage().contains("Invalid"), "Expected invalid credentials error");
    }
}
