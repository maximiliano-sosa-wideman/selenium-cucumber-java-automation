package hellocucumber.stepDefinitions.loginStepDefs;

import hellocucumber.utils.DriverFactory;
import hellocucumber.utils.ReadProperties;
import hellocucumber.utils.RequestFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginUIStepDef {

    ReadProperties properties = ReadProperties.getInstance();

    DriverFactory driverFactory;
    RequestFactory requestFactory;

    public LoginUIStepDef(RequestFactory requestFactory, DriverFactory driverFactory){
        this.requestFactory = requestFactory;
        this.driverFactory = driverFactory;
    }

    @Given("a valid user")
    public void a_valid_user() {
        this.driverFactory.getLoginPage().showLoginPage();
    }

    @When("a valid email is inputted")
    public void a_valid_email_is_inputed() {
        this.driverFactory.getLoginPage().setEmail(properties.getProperty("VALID_EMAIL"));
    }

    @When("a valid password is inputted")
    public void a_valid_password_is_inputed() {
        this.driverFactory.getLoginPage().setPassword(System.getenv("VALID_PASSWORD"));
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        this.driverFactory.getLoginPage().clickLogin();
    }

    @Then("the user logs in")
    public void theUserLogsIn() {
        Assertions.assertTrue(this.driverFactory.getLoginPage().isUserloggedin());
    }

    @Given("a invalid user tries to log in")
    public void aInvalidUserTriesToLogIn() {
        this.driverFactory.getLoginPage().showLoginPage();
    }

    @When("the user enters the wrong email as {string}")
    public void theUserEntersTheWrongEmailAsEmail(String email) {
        this.driverFactory.getLoginPage().setEmail(email);
    }

    @And("the user enters the wrong password as {string}")
    public void theUserEntersTheWrongPasswordAsPassword(String password) {
        this.driverFactory.getLoginPage().setPassword(password);
    }

    @Then("the user cannot log in")
    public void theUserCannotLogIn() {
        Assertions.assertTrue(this.driverFactory.getLoginPage().failedLogin());
    }

}
