package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage extends BasePage {

    private final Locator userMenu;
    private final Locator logoutLink;
    private final Locator welcomeBanner;
    private final Locator searchBox;
    private final Locator searchButton;

    private final String url;

    public HomePage(Page page) {
        super(page);
        this.url = "/";
        this.userMenu = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("User Menu"));
        this.logoutLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"));
        this.welcomeBanner = page.getByTestId("welcome-banner");
        this.searchBox = page.getByPlaceholder("Search");
        this.searchButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search"));
    }

    public HomePage navigate(String baseUrl) {
        page.navigate(baseUrl + url);
        return this;
    }

    public boolean isLoggedIn() {
        return userMenu.isVisible();
    }

    public LoginPage logout() {
        userMenu.click();
        logoutLink.click();
        return new LoginPage(page);
    }

    public void search(String text) {
        searchBox.fill(text);
        searchButton.click();
    }

    public String getWelcomeText() {
        return welcomeBanner.innerText();
    }
}
