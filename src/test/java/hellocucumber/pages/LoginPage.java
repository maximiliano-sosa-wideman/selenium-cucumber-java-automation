package hellocucumber.pages;

import hellocucumber.utils.UtilMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperMethods;

public class LoginPage extends BasePage {

    HelperMethods helperMethods;
    UtilMethods util = new UtilMethods();
    static final String LOGIN_URL = "https://club-administration.qa.qubika.com/#/auth/login";

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='email']")
    private WebElement emailField;
    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='password']")
    private WebElement passwordField;
    @FindBy(how = How.XPATH, using = "//*[@id='sidenav-main']")
    private WebElement dashboardLink;
    @FindBy(how = How.XPATH, using = "//*[@type='submit']")
    private WebElement authButton;
    @FindBy(how = How.XPATH, using = "//*[@role='alertdialog']")
    private WebElement errorToast;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        helperMethods = new HelperMethods(driver);
    }

    public void showLoginPage(WebDriver driver){
        driver.get(LOGIN_URL);
        driver.manage().window().maximize();
    }

    public void closeWindow(WebDriver driver){
        driver.close();
    }

    public void setEmail(String email, WebDriver driver){
        util.writeInput(emailField, email, driver);
    }

    public void setPassword(String password, WebDriver driver){
        util.writeInput(passwordField, password, driver);
    }

    public void clickLogin(WebDriver driver){
        util.clickElement(authButton, driver);
    }

    public boolean isUserloggedin(WebDriver driver){
        return util.waitUntilDisplayed(dashboardLink, driver);
    }

    public boolean failedLogin(WebDriver driver){
        return util.waitUntilDisplayed(errorToast, driver);
    }
}
