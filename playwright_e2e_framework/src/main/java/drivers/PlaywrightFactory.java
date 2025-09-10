package drivers;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    private static final ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThread = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThread = new ThreadLocal<>();

    public static void initPlaywright() {
        playwrightThread.set(Playwright.create());
    }

    public static Playwright getPlaywright() {
        if (playwrightThread.get() == null) {
            initPlaywright();
        }
        return playwrightThread.get();
    }

    public static void setBrowser(Browser browser) {
        browserThread.set(browser);
    }

    public static Browser getBrowser() {
        return browserThread.get();
    }

    public static void setBrowserContext(BrowserContext context) {
        contextThread.set(context);
    }

    public static BrowserContext getBrowserContext() {
        return contextThread.get();
    }

    public static void setPage(Page page) {
        pageThread.set(page);
    }

    public static Page getPage() {
        return pageThread.get();
    }

    public static void cleanup() {
        if (pageThread.get() != null) {
            pageThread.get().close();
            pageThread.remove();
        }
        if (contextThread.get() != null) {
            contextThread.get().close();
            contextThread.remove();
        }
        if (browserThread.get() != null) {
            browserThread.get().close();
            browserThread.remove();
        }
        if (playwrightThread.get() != null) {
            playwrightThread.get().close();
            playwrightThread.remove();
        }
    }
}
