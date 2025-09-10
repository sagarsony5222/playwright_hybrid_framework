package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.WaitUtil;

public abstract class BasePage {
    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    // Common actions
    protected void click(Locator locator) {
        locator.click();
    }

    protected void type(Locator locator, String text) {
        locator.fill(text);
    }

    protected String getText(Locator locator) {
        return locator.innerText();
    }

    // Wait helpers
    protected void waitVisible(Locator locator, int timeoutMs) {
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.valueOf("visible")).setTimeout((double) timeoutMs));
    }

    public String getTitle() {
        return page.title();
    }

    public String getUrl() {
        return page.url();
    }
}
