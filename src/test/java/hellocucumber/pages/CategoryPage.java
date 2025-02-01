package hellocucumber.pages;

import hellocucumber.utils.UtilMethods;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CategoryPage extends BasePage {

    UtilMethods util;

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

    public CategoryPage(WebDriver driver, Scenario scenario) {
        super(driver, scenario);
        util = new UtilMethods(driver);
    }

    public void showCategoryPage(String responseBody){

        util.setAuthToken(responseBody);
        util.navigateTo(CATEGORY_URL);

    }

    public void clickAddCategory(){
        util.clickElement(ADD_CATEGORY_BUTTON);

    }

    public void fillCategoryName(String categoryName){
        util.writeInput(CATEGORY_NAME_FIELD, categoryName);
    }

    public void clickCreateCategory(){
        util.clickElement(ACCEPT_CATEGORY_BUTTON);
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
