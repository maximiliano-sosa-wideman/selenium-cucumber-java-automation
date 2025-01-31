package hellocucumber.stepDefinitions.loginStepDefs;

import hellocucumber.pages.LoginPage;
import hellocucumber.utils.ReadProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginUIStepDef {

    static LoginPage page;
    static WebDriver driver;
    ReadProperties properties = ReadProperties.getInstance();

    @Before("@Ui")
    public void setUpPage(){
        driver = new ChromeDriver();
        page = new LoginPage(driver);
    }

    @After("@Ui")
    public void refreshWindow(){
        driver.navigate().refresh();
    }

    @AfterAll
    public static void closeDriver(){
        page.closeWindow(driver);
    }

    @Given("a valid user")
    public void a_valid_user() {
        page.showLoginPage(driver);
    }

    @When("a valid email is inputted")
    public void a_valid_email_is_inputed() {
        page.setEmail(properties.getProperty("VALID_EMAIL"), driver);
    }

    @When("a valid password is inputted")
    public void a_valid_password_is_inputed() {
        page.setPassword(properties.getProperty("VALID_PASSWORD"), driver);
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        //Actually logging in
        page.clickLogin(driver);
    }

    @Then("the user logs in")
    public void theUserLogsIn() {
        //Asserts that the user has logged in
        Assertions.assertTrue(page.isUserloggedin(driver));
    }

    @Given("a invalid user tries to log in")
    public void aInvalidUserTriesToLogIn() {
        page.showLoginPage(driver);
    }

    @When("the user enters the wrong email as {string}")
    public void theUserEntersTheWrongEmailAsEmail(String email) {
        page.setEmail(email, driver);
    }

    @And("the user enters the wrong password as {string}")
    public void theUserEntersTheWrongPasswordAsPassword(String password) {
        page.setPassword(password, driver);
    }

    @Then("the user cannot log in")
    public void theUserCannotLogIn() {
        Assertions.assertTrue(page.failedLogin(driver));
    }

}
