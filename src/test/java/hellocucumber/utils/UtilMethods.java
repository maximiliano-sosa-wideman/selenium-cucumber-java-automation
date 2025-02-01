package hellocucumber.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.http.HttpResponse;
import java.time.Duration;

public class UtilMethods {

    WebDriverWait wait;
    WebDriver driver;
    protected RequestFactory requestFactory;

    public UtilMethods(WebDriver driver, RequestFactory requestFactory){
        this.driver = driver;
        this.requestFactory = requestFactory;
        PageFactory.initElements(driver, this);
    }

    public UtilMethods(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public UtilMethods(){
        PageFactory.initElements(driver, this);
    }

    // Get the token from the login to use it to access other pages more quickly
    public String getTokenFromLogin(HttpResponse<String> response){
        JSONObject jsonResponse = new JSONObject(response.body());
        return jsonResponse.get("token").toString();
    }

    public String extractToken(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getString("token");
    }

    private JavascriptExecutor createJSE(){
        if (this.driver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) this.driver);
        } else {
            throw new IllegalStateException("This driver does not support JavaScript...!");
        }
    }

    public void navigateTo(String url){
        this.driver.get(url);
    }

    public void setAuthToken(String responseBody){
        JavascriptExecutor jexecutor = createJSE();
        jexecutor.executeScript("window.localStorage.setItem('0.0.1', '" + responseBody + "')");
    }

    public static ChromeOptions chromeOptionsConfig() {
        ChromeOptions options = new ChromeOptions();

        /**
         * I'll leave this piece of code commented while I find a real justified reason to have this uncommented
         */
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--disable-browser-side-navigation");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu");
////		if (!getEnvironmentVariable(PLATFORM_GOAL_EXECUTION_TEST).equalsIgnoreCase(localEnvironment)) {
//        if (!"LOCAL".equalsIgnoreCase(localEnvironment)) {
//            options.addArguments("--headless");
//        }
//        options.addArguments("window-size=1980,1080");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-setuid-sandbox");
        return options;
    }

    public String getLastCategoryIDFromList(String jsonString){
        return getValueFromKey("id", jsonString);
    }

    public String getLastCategoryNameFromList(String jsonString){
        return getValueFromKey("name", jsonString);
    }

    private String getValueFromKey(String key, String jsonString){
        JSONArray jsonArray = new JSONArray(jsonString);

        String value = null;
        for (int i = jsonArray.length() - 1; i >= 0; i--) {
            JSONObject item = jsonArray.getJSONObject(i);
            if (item.has(key) && !item.isNull(key)) {
                value = item.getString(key);
                break;
            }
        }
        return value;
    }

    public void clickElement(WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void writeInput(WebElement element, String inputValue){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(inputValue);
    }

    public boolean waitUntilDisplayed(WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.isDisplayed();
    }

}

