package hellocucumber.categoryStepDefs;

import hellocucumber.endpoints.LoginEPs;
import hellocucumber.utils.UtilMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.HelperMethods;

import java.net.http.HttpResponse;

public class CategoryUI {

    LoginEPs loginEP;
    UtilMethods util = new UtilMethods();
    String token;

    @Given("a logged in user")
    public void a_Logged_In_User() {

        loginEP = new LoginEPs();

        HttpResponse<String> response = loginEP.login(System.getenv("VALID_EMAIL"), System.getenv("VALID_PASSWORD"));

        token = util.getTokenFromLogin(response);

    }

    @When("they create a category")
    public void they_Create_A_Category() {

    }

    @Then("the category can be seen in the CategoryUI table")
    public void the_Category_Can_Be_Seen_In_The_Category_Table() {

    }
}
