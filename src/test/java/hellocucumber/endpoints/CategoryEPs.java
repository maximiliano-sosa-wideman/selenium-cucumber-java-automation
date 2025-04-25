package hellocucumber.endpoints;

import hellocucumber.utils.BaseRequest;
import hellocucumber.utils.DriverFactory;
import hellocucumber.utils.ReadProperties;
import hellocucumber.utils.UtilMethods;
import hellocucumber.utils.constants.DataConstants;
import net.bytebuddy.asm.MemberSubstitution;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Driver;

public class CategoryEPs {

    HttpClient client;
    BaseRequest baseRequest;
    UtilMethods utils = new UtilMethods();
    private String token = "";

    // not yet used
    public HttpResponse<String> createCategory(String name, boolean root){
        // Setups the request body
        String requestBody = new JSONObject()
                .put("name", name)
                .put("root", root)
                .toString();

        // prepares the request with the supplied information.
        baseRequest = new BaseRequest();
        HttpRequest request = baseRequest.prepareRequest(DataConstants.CREATE_CATEGORY_EP, requestBody);

        // instantiates a new client and sends the request
        client = HttpClient.newHttpClient();
        return baseRequest.sendRequest(client, request);
    }

    public HttpResponse<String> listAllCategories(WebDriver driver){
        String storedKeyValue = utils.getAuthToken(driver);
        token = utils.extractToken(storedKeyValue);

        baseRequest = new BaseRequest();
        HttpRequest request = baseRequest.prepareGETRequest(DataConstants.LIST_ALL_CATEGORIES, token);
        client = HttpClient.newHttpClient();
        return baseRequest.sendRequest(client, request);
    }

    public void deleteCategory(WebDriver driver, String id){

        String storedKeyValue = utils.getAuthToken(driver);
        token = utils.extractToken(storedKeyValue);

        baseRequest = new BaseRequest();
        HttpRequest request = baseRequest.prepareDELETERequest(DataConstants.DELETE_CATEGORY + id, token);
        client = HttpClient.newHttpClient();
        baseRequest.sendRequest(client, request);
    }
}
