package utils;

import io.qameta.allure.Allure;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportUtil {

    public static void attachAllureScreenshot(String name) {
        try {
            byte[] screenshot = ScreenshotUtil.takeScreenshotAsBytes();
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            throw new RuntimeException("Failed to attach screenshot to Allure report", e);
        }
    }

    public static void attachLogs(String name, String content) {
        Allure.addAttachment(name, "text/plain", content, ".txt");
    }

    public static void attachTextFile(String name, String filePath) {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            Allure.addAttachment(name, new ByteArrayInputStream(fileContent));
        } catch (Exception e) {
            throw new RuntimeException("Failed to attach file to Allure report", e);
        }
    }

    public static void onTestFailure(ITestResult result) {
        attachAllureScreenshot("Failure_Screenshot");
        attachLogs("TestNG Logs", result.getThrowable().toString());
    }
}
