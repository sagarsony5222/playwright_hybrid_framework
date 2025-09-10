package dataproviders;

import org.testng.annotations.DataProvider;
import utils.TestDataReader;

import java.util.Map;

public class TestDataProvider {

    @DataProvider(name = "loginDataJson")
    public Object[][] loginDataJson() {
        Map<String, Object> creds = TestDataReader.getJsonData("login");
        // Expecting {"login": {"validUser":"...", "validPass":"...", "invalidUser":"...", "invalidPass":"..."}}
        return new Object[][]{
                {creds.get("validUser"), creds.get("validPass"), true},
                {creds.get("invalidUser"), creds.get("invalidPass"), false}
        };
    }

    @DataProvider(name = "loginDataExcel")
    public Object[][] loginDataExcel() {
        // Expect Excel sheet "Login" with columns: username,password,expected
        return TestDataReader.getExcelData("Login");
    }
}
