package hellocucumber.stepDefinitions.loginStepDefs;

import hellocucumber.endpoints.LoginEPs;
import hellocucumber.utils.DriverFactory;
import hellocucumber.utils.ReadProperties;
import hellocucumber.utils.RequestFactory;
import hellocucumber.utils.UtilMethods;
import hellocucumber.utils.constants.DataConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.net.http.HttpResponse;

public class LoginAPIStepDef {

    LoginEPs loginEP;
    public String loginToken;

    DriverFactory driverFactory;
    RequestFactory requestFactory;

    public LoginAPIStepDef(RequestFactory requestFactory, DriverFactory driverFactory){
        this.requestFactory = requestFactory;
        this.driverFactory = driverFactory;
    }

    @Given("a user wanting to log in by API")
    public void aUserWantingToLogIn() {
        // stub
    }

    @When("a valid email and password is passed to the login endpoint")
    public void aValidEmailAndPasswordIsPassedToTheLoginEndpoint() {
        loginEP = new LoginEPs();
        HttpResponse<String> response = loginEP.login(DataConstants.VALID_EMAIL, System.getenv("VALID_PASSWORD"));
        loginToken = this.driverFactory.getUtilMethods().getTokenFromLogin(response);
    }

    @Then("the user is logged in")
    public void theUserIsLoggedIn() {
        Assertions.assertFalse(loginToken.isEmpty());
    }
}

