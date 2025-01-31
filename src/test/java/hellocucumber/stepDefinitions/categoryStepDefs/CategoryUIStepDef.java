package hellocucumber.stepDefinitions.categoryStepDefs;

import hellocucumber.endpoints.CategoryEPs;
import hellocucumber.endpoints.LoginEPs;
import hellocucumber.pages.CategoryPage;
import hellocucumber.utils.ReadProperties;
import hellocucumber.utils.UtilMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.http.HttpResponse;

public class CategoryUIStepDef {

    static LoginEPs loginEP;
    static CategoryPage page;
    static CategoryEPs catEPs;
    static WebDriver driver;
    UtilMethods util = new UtilMethods();
    String token;
    private String categoryName = "CategoriaTestMaxiTEST2";
    private String categoryID = "";
    ReadProperties properties = ReadProperties.getInstance();

    @Before
    public void setUpPage(){
        driver = new ChromeDriver();
        page = new CategoryPage(driver);
        catEPs = new CategoryEPs();
    }

    @After
    public void deleteCategory(){
        catEPs.deleteCategory(driver, categoryID);
        page.closeWindow(driver);
    }

    @Given("a logged in user")
    public void aLoggedInUser() {
        loginEP = new LoginEPs();
        HttpResponse<String> response = loginEP.login(properties.getProperty("VALID_EMAIL"), properties.getProperty("VALID_PASSWORD"));
//                System.getenv("VALID_EMAIL"),
//                System.getenv("VALID_PASSWORD"));

        token = response.body();
    }

    @When("they create a category")
    public void theyCreateACategory() {
        page.showCategoryPage(driver, token);
        page.clickAddCategory(driver);
    }

    @And("they write a valid name")
    public void theyWriteAValidName() {
        page.fillCategoryName(driver, categoryName);
    }

    @And("they click the accept button")
    public void theyClickTheAcceptButton() {
        page.clickCreateCategory(driver);
    }

    @Then("the category creates and can be seen in the CategoryUI table")
    public void theCategoryCanBeSeenInTheCategoryTable() {

        // meanwhile we do a way of validating via UI, I validate this via API

        HttpResponse<String> response = catEPs.listAllCategories(driver);
        categoryID = util.getLastCategoryIDFromList(response.body());
        Assertions.assertEquals(categoryName, util.getLastCategoryNameFromList(response.body()));
    }
}
