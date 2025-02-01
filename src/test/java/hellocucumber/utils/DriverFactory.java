package hellocucumber.utils;

import hellocucumber.pages.CategoryPage;
import hellocucumber.pages.LoginPage;
import io.cucumber.java.Scenario;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

@Data
public class DriverFactory {
    private RemoteWebDriver driver;
    private RequestFactory requestFactory;
    private Scenario scenario;
    private LoginPage loginPage;
    private UtilMethods utilMethods;
    private CategoryPage categoryPage;


    public DriverFactory(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public void InitializePageObject(WebDriver driver, Scenario scenario) {
        setLoginPage(new LoginPage(driver, scenario));
        setUtilMethods(new UtilMethods(driver, getRequestFactory()));
        setCategoryPage(new CategoryPage(driver, scenario));
    }
}
