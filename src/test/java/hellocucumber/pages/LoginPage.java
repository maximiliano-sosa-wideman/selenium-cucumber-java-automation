package hellocucumber.pages;

import hellocucumber.utils.UtilMethods;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
//import utils.HelperMethods;

public class LoginPage extends BasePage {

    UtilMethods util;

    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='email']")
    private WebElement EMAIL_FIELD;
    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='password']")
    private WebElement PASSWORD_FIELD;
    @FindBy(how = How.XPATH, using = "//*[@id='sidenav-main']")
    private WebElement DASHBOARD_LINK;
    @FindBy(how = How.XPATH, using = "//*[@type='submit']")
    private WebElement AUTH_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[@role='alertdialog']")
    private WebElement ERROR_TOAST;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
        util = new UtilMethods(driver);
    }

    public void showLoginPage(){
    }

    public void setEmail(String email){
        util.writeInput(EMAIL_FIELD, email);
    }

    public void setPassword(String password){
        util.writeInput(PASSWORD_FIELD, password);
    }

    public void clickLogin(){
        util.clickElement(AUTH_BUTTON);
    }

    public boolean isUserloggedin(){
        return util.waitUntilDisplayed(DASHBOARD_LINK);
    }

    public boolean failedLogin(){
        return util.waitUntilDisplayed(ERROR_TOAST);
    }
}
