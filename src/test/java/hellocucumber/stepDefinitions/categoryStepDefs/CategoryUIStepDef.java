package hellocucumber.stepDefinitions.categoryStepDefs;

import hellocucumber.endpoints.CategoryEPs;
import hellocucumber.endpoints.LoginEPs;
import hellocucumber.utils.DriverFactory;
import hellocucumber.utils.ReadProperties;
import hellocucumber.utils.RequestFactory;
import hellocucumber.utils.context.ScenarioContextInfoHolder;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.net.http.HttpResponse;

public class CategoryUIStepDef {

    private String categoryName = "CategoriaTestMaxiTEST4";
    private String categoryID = "";

    ReadProperties properties = ReadProperties.getInstance();

    String token;
    DriverFactory driverFactory;
    RequestFactory requestFactory;
    LoginEPs loginEP;
    CategoryEPs catEP;

    public CategoryUIStepDef(RequestFactory requestFactory, DriverFactory driverFactory){
        this.loginEP = new LoginEPs();
        this.catEP = new CategoryEPs();
        this.requestFactory = requestFactory;
        this.driverFactory = driverFactory;
    }

    @Given("a logged in user")
    public void aLoggedInUser() {
        HttpResponse<String> response = loginEP.login(properties.getProperty("VALID_EMAIL"), properties.getProperty("VALID_PASSWORD"));
//                System.getenv("VALID_EMAIL"),
//                System.getenv("VALID_PASSWORD"));

        token = response.body();
    }

    @When("they create a category")
    public void theyCreateACategory() {
        this.driverFactory.getCategoryPage().showCategoryPage(token);
        this.driverFactory.getCategoryPage().clickAddCategory();
    }

    @And("they write a valid name")
    public void theyWriteAValidName() {
        this.driverFactory.getCategoryPage().fillCategoryName(categoryName);
//        page.fillCategoryName(driver, categoryName);
    }

    @And("they click the accept button")
    public void theyClickTheAcceptButton() {
        this.driverFactory.getCategoryPage().clickCreateCategory();
//        page.clickCreateCategory(driver);
    }

    @Then("the category creates and can be seen in the CategoryUI table")
    public void theCategoryCanBeSeenInTheCategoryTable() {

        // meanwhile we do a way of validating via UI, I validate this via API

        HttpResponse<String> response = catEP.listAllCategories(this.driverFactory.getDriver());
//        categoryID = util.getLastCategoryIDFromList(response.body());
        categoryID = this.driverFactory.getUtilMethods().getLastCategoryIDFromList(response.body());
//        Assertions.assertEquals(categoryName, util.getLastCategoryNameFromList(response.body()));
        Assertions.assertEquals(categoryName, this.driverFactory.getUtilMethods().getLastCategoryNameFromList(response.body()));
    }
    @After
    public void deleteCategory(){
        catEP.deleteCategory(this.driverFactory.getDriver(), categoryID);
    }
}
