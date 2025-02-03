package hellocucumber.stepDefinitions.loginStepDefs;

import hellocucumber.endpoints.LoginEPs;
import hellocucumber.utils.DriverFactory;
import hellocucumber.utils.ReadProperties;
import hellocucumber.utils.RequestFactory;
import hellocucumber.utils.UtilMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.net.http.HttpResponse;

public class LoginAPIStepDef {

    LoginEPs loginEP;
    public String loginToken;
    UtilMethods util;
    ReadProperties properties = ReadProperties.getInstance();

    private static final String LOGIN_EP_URL = "https://api.club-administration.qa.qubika.com/api/auth/login";
    DriverFactory driverFactory;
    RequestFactory requestFactory;

    public LoginAPIStepDef(RequestFactory requestFactory, DriverFactory driverFactory){
        this.requestFactory = requestFactory;
        this.driverFactory = driverFactory;
    }

    @Given("a user wanting to log in by API")
    public void aUserWantingToLogIn() {

    }

    @When("a valid email and password is passed to the login endpoint")
    public void aValidEmailAndPasswordIsPassedToTheLoginEndpoint() {

        // instantiates a new LoginEPs
        loginEP = new LoginEPs();

        // sets up a variable to save the response and sends the meaningful test information (email/username and password)
        HttpResponse<String> response = loginEP.login(properties.getProperty("VALID_EMAIL"), System.getenv("VALID_PASSWORD"));

        loginToken = this.driverFactory.getUtilMethods().getTokenFromLogin(response);
    }

    @Then("the user is logged in")
    public void theUserIsLoggedIn() {
        Assertions.assertFalse(loginToken.isEmpty());
    }
}

