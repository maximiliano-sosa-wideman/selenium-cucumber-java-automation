package hellocucumber.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.http.HttpResponse;
import java.time.Duration;

public class UtilMethods {

    WebDriverWait wait;

    // Get the token from the login to use it to access other pages more quickly
    public String getTokenFromLogin(HttpResponse<String> response){
        JSONObject jsonResponse = new JSONObject(response.body());
        return jsonResponse.get("token").toString();
    }

    public String extractToken(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getString("token");
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

    public void clickElement(WebElement element, WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void writeInput(WebElement element, String inputValue, WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(inputValue);
    }

    public boolean waitUntilDisplayed(WebElement element, WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.isDisplayed();
    }

}

