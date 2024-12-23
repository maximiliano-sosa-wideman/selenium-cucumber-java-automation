package hellocucumber.loginStepDefs;

import hellocucumber.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Login {

    static LoginPage page;
    static WebDriver driver;

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
        page.CloseWindow(driver);
    }

    @Given("a valid user")
    public void a_valid_user() {
        page.ShowLoginPage(driver);
    }

    @When("a valid email is inputted")
    public void a_valid_email_is_inputed() {
        page.setEmail(driver, System.getenv("VALID_EMAIL"));
    }

    @When("a valid password is inputted")
    public void a_valid_password_is_inputed() {
        page.setPassword(driver, System.getenv("VALID_PASSWORD"));
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        //Actually logging in
        page.ClickLogin(driver);
    }

    @Then("the user logs in")
    public void the_user_logs_in() {
        //Asserts that the user has logged in
        Assertions.assertTrue(page.IsUserLoggedIn(driver));
    }

    @Given("a invalid user tries to log in")
    public void aInvalidUserTriesToLogIn() {
        page.ShowLoginPage(driver);
    }

    @When("the user enters the wrong email as {string}")
    public void theUserEntersTheWrongEmailAsEmail(String email) {
        page.setEmail(driver, email);
    }

    @And("the user enters the wrong password as {string}")
    public void theUserEntersTheWrongPasswordAsPassword(String password) {
        page.setPassword(driver, password);
    }

    @Then("the user cannot log in")
    public void theUserCannotLogIn() {
        Assertions.assertTrue(page.failedLogin(driver));
    }

}
