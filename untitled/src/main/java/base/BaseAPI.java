package base;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseAPI {

    protected ConfigReader config;

    @BeforeSuite
    public void setupRestAssured() {
        config = new ConfigReader();
        // Set base URI and common settings
        RestAssured.baseURI = config.getProperty("apiBaseUrl");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @AfterSuite
    public void teardownRestAssured() {
        // Reset RestAssured settings
        RestAssured.reset();
    }
}
