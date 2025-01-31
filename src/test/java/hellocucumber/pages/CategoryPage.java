package hellocucumber.pages;

import hellocucumber.utils.UtilMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HelperMethods;

import java.time.Duration;

public class CategoryPage extends BasePage {


    HelperMethods helperMethods;
    UtilMethods util = new UtilMethods();

    static final String LOGIN_URL = "https://club-administration.qa.qubika.com/#/auth/login";
    static final String CATEGORY_URL = "https://club-administration.qa.qubika.com/#/category-type";

    @FindBy(how = How.XPATH, using = "//*[text()=' Adicionar']")
    private WebElement addCategoryButton;
    @FindBy(how = How.XPATH, using = "//*[@role='alertdialog']")
    private WebElement errorToast;
    @FindBy(how = How.XPATH, using = "//*[@id='input-username']")
    private WebElement categoryNameField;
    @FindBy(how = How.XPATH, using = "//*[@id='customCheckMain']")
    private WebElement subcategoryCheck;
    @FindBy(how = How.XPATH, using = "//*[text()='Aceptar']")
    private WebElement acceptCategoryButton;
    @FindBy(how = How.XPATH, using = "//*[text()='Cancelar']")
    private WebElement cancelCategoryButton;

    public CategoryPage(WebDriver driver) {
        super(driver);
        helperMethods = new HelperMethods(driver);
    }

    public void showCategoryPage(WebDriver driver, String responseBody){

        driver.get(LOGIN_URL);

        JavascriptExecutor jexecutor = (JavascriptExecutor) driver;
        jexecutor.executeScript("window.localStorage.setItem('0.0.1', '" + responseBody + "')");

        driver.manage().window().maximize();
        driver.get(CATEGORY_URL);
    }

    public void clickAddCategory(WebDriver driver){
        util.clickElement(addCategoryButton,driver);

    }

    public void fillCategoryName(WebDriver driver, String categoryName){
        util.writeInput(categoryNameField, categoryName, driver);
    }

    public void clickCreateCategory(WebDriver driver){
        util.clickElement(acceptCategoryButton,driver);
    }

    // not used
    public void clickCancelCategory(){
        cancelCategoryButton.click();
    }

    //not used
    public void selectSubCategory(Boolean isSub){
        if(isSub){
            subcategoryCheck.click(); // replace by util.clickELement() method
        }
    }

    public void closeWindow(WebDriver driver){
        driver.close();
    }

}
