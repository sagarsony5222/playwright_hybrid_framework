package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import config.ConfigReader;
import drivers.PlaywrightFactory;
import org.testng.annotations.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected ConfigReader config;

    @BeforeSuite
    public void globalSetup() {
        // Initialize configuration reader
        config = new ConfigReader();

        // Create Playwright instance and store in ThreadLocal
        PlaywrightFactory.initPlaywright();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chromium") String browserName) throws Exception {
        // Get BrowserType using reflection and cast
        BrowserType browserType = (BrowserType) PlaywrightFactory.getPlaywright()
                .getClass()
                .getMethod(browserName)
                .invoke(PlaywrightFactory.getPlaywright());

        // Launch browser
        Browser browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(config.getProperty("headless"))));
        PlaywrightFactory.setBrowser(browser);

        // Create new browser context
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        PlaywrightFactory.setBrowserContext(context);

        // Open new page
        Page page = context.newPage();
        PlaywrightFactory.setPage(page);

        // Navigate to base URL
        page.navigate(config.getProperty("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close context and browser per test
        if (PlaywrightFactory.getBrowserContext() != null) {
            PlaywrightFactory.getBrowserContext().close();
        }
        if (PlaywrightFactory.getBrowser() != null) {
            PlaywrightFactory.getBrowser().close();
        }
    }

    @AfterSuite
    public void globalTearDown() {
        // Cleanup Playwright
        PlaywrightFactory.cleanup();
    }
}
