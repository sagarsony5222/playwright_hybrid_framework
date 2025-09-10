package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // Locators using user-facing attributes where possible
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator signInButton;
    private final Locator errorBanner;

    // Optional: page URL (if direct navigation is needed)
    private final String url;

    public LoginPage(Page page) {
        super(page);
        this.url = "/login"; // can be resolved against baseUrl in tests
        this.emailInput = page.getByLabel("Email");
        this.passwordInput = page.getByLabel("Password");
        this.signInButton = page.getByRole(com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Sign In"));
        this.errorBanner = page.getByTestId("login-error");
    }

    public LoginPage navigate(String baseUrl) {
        page.navigate(baseUrl + url);
        return this;
    }

    public LoginPage enterEmail(String email) {
        type(emailInput, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public HomePage clickSignIn() {
        click(signInButton);
        return new HomePage(page);
    }

    public String getErrorMessage() {
        return errorBanner.isVisible() ? errorBanner.innerText() : "";
    }

    public boolean isLoaded() {
        return emailInput.isVisible() && passwordInput.isVisible();
    }
}
