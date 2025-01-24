package hellocucumber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperMethods;

import java.time.Duration;

public class LoginPage extends BasePage {

    HelperMethods helperMethods;
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

    public void ShowLoginPage(WebDriver driver){
        driver.get(LOGIN_URL);
        driver.manage().window().maximize();
    }

    public void CloseWindow(WebDriver driver){
        driver.close();
    }

    public void setEmail(String email){
        emailField.sendKeys(email);
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }

    public void ClickLogin(){
        authButton.click();
    }

    public boolean IsUserLoggedIn(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(dashboardLink));
        return dashboardLink.isDisplayed();
    }

    public boolean failedLogin(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(errorToast));
        return errorToast.isDisplayed();
    }
}
