package utils;

import drivers.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitUtil {
    private static final Page page = PlaywrightFactory.getPage();

    public static void waitForVisible(String selector, int timeoutMs) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeoutMs));
    }

    public static void waitForClickable(String selector, int timeoutMs) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(timeoutMs));
    }

    public static void waitForLoadState(com.microsoft.playwright.options.LoadState state, int timeoutMs) {
        page.waitForLoadState(state, new Page.WaitForLoadStateOptions().setTimeout(timeoutMs));
    }
}
