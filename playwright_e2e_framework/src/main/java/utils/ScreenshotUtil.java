package utils;

import drivers.PlaywrightFactory;
import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;
import java.util.Base64;

public class ScreenshotUtil {
    private static final String SCREENSHOT_DIR = "screenshots/";

    public static String takeScreenshot(String fileName) {
        try {
            Page page = PlaywrightFactory.getPage();
            File dest = Paths.get(SCREENSHOT_DIR, fileName).toFile();
            page.screenshot(new Page.ScreenshotOptions().setPath(dest.toPath()));
            return dest.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Failed to take screenshot", e);
        }
    }

    public static byte[] takeScreenshotAsBytes() {
        try {
            Page page = PlaywrightFactory.getPage();
            return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        } catch (Exception e) {
            throw new RuntimeException("Failed to take screenshot as bytes", e);
        }
    }
}
