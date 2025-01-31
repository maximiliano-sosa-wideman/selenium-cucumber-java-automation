package hellocucumber.pages;

import hellocucumber.utils.UtilMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.HelperMethods;

public class CategoryPage extends BasePage {


    HelperMethods helperMethods;
    UtilMethods util = new UtilMethods();

    static final String LOGIN_URL = "https://club-administration.qa.qubika.com/#/auth/login";
    static final String CATEGORY_URL = "https://club-administration.qa.qubika.com/#/category-type";

    @FindBy(how = How.XPATH, using = "//*[text()=' Adicionar']")
    private WebElement ADD_CATEGORY_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[@id='input-username']")
    private WebElement CATEGORY_NAME_FIELD;
    @FindBy(how = How.XPATH, using = "//*[@id='customCheckMain']")
    private WebElement SUBCATEGORY_CHECK;
    @FindBy(how = How.XPATH, using = "//*[text()='Aceptar']")
    private WebElement ACCEPT_CATEGORY_BUTTON;
    @FindBy(how = How.XPATH, using = "//*[text()='Cancelar']")
    private WebElement CANCEL_CATEGORY_BUTTON;

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
        util.clickElement(ADD_CATEGORY_BUTTON,driver);

    }

    public void fillCategoryName(WebDriver driver, String categoryName){
        util.writeInput(CATEGORY_NAME_FIELD, categoryName, driver);
    }

    public void clickCreateCategory(WebDriver driver){
        util.clickElement(ACCEPT_CATEGORY_BUTTON,driver);
    }

    // not used
    public void clickCancelCategory(){
        CANCEL_CATEGORY_BUTTON.click();
    }

    //not used
    public void selectSubCategory(Boolean isSub){
        if(isSub){
            SUBCATEGORY_CHECK.click(); // replace by util.clickELement() method
        }
    }

    public void closeWindow(WebDriver driver){
        driver.close();
    }

}
