package drivers;

import config.ConfigReader;
import com.microsoft.playwright.*;

public class DriverManager {

    private final ConfigReader config = new ConfigReader();

    public void launchBrowser(String browserName) {
        Playwright playwright = PlaywrightFactory.getPlaywright();
        BrowserType browserType = switch (browserName.toLowerCase()) {
            case "chromium", "chrome" -> playwright.chromium();
            case "firefox" -> playwright.firefox();
            case "webkit", "safari" -> playwright.webkit();
            default -> throw new RuntimeException("Unsupported browser: " + browserName);
        };

        Browser browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(config.getProperty("headless")))
                .setSlowMo(Long.parseLong(config.getProperty("slowMo"))));
        PlaywrightFactory.setBrowser(browser);

        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(
                        Integer.parseInt(config.getProperty("viewportWidth")),
                        Integer.parseInt(config.getProperty("viewportHeight"))
                )
                .setIgnoreHTTPSErrors(true)
        );
        PlaywrightFactory.setBrowserContext(context);

        Page page = context.newPage();
        PlaywrightFactory.setPage(page);
    }
}
