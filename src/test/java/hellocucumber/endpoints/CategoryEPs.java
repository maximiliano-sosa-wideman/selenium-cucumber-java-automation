package hellocucumber.endpoints;

import hellocucumber.utils.BaseRequest;
import hellocucumber.utils.UtilMethods;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CategoryEPs {

    HttpClient client;
    BaseRequest baseRequest;
    UtilMethods utils = new UtilMethods();

    private String token = "";

    private static final String CREATE_CATEGORY_EP = "https://api.club-administration.qa.qubika.com/api/category-type/create";
    private static final String LIST_ALL_CATEGORIES = "https://api.club-administration.qa.qubika.com/api/category-type";
    private static final String DELETE_CATEGORY = "https://api.club-administration.qa.qubika.com/api/category-type/delete/";

    // not yet used
    public HttpResponse<String> createCategory(String name, boolean root){
        // Setups the request body
        String requestBody = new JSONObject()
                .put("name", name)
                .put("root", root)
                .toString();

        // prepares the request with the supplied information.
        baseRequest = new BaseRequest();
        HttpRequest request = baseRequest.prepareRequest(CREATE_CATEGORY_EP, requestBody);

        // instantiates a new client and sends the request
        client = HttpClient.newHttpClient();
        return baseRequest.sendRequest(client, request);
    }

    public HttpResponse<String> listAllCategories(WebDriver driver){
        JavascriptExecutor jexecutor = (JavascriptExecutor) driver;
        String storedKeyValue = jexecutor.executeScript("return window.localStorage.getItem('0.0.1');").toString();
        token = utils.extractToken(storedKeyValue);

        baseRequest = new BaseRequest();
        HttpRequest request = baseRequest.prepareGETRequest(LIST_ALL_CATEGORIES, token);
        client = HttpClient.newHttpClient();
        return baseRequest.sendRequest(client, request);
    }

    public void deleteCategory(WebDriver driver, String id){
        JavascriptExecutor jexecutor = (JavascriptExecutor) driver;
        String storedKeyValue = jexecutor.executeScript("return window.localStorage.getItem('0.0.1');").toString();
        token = utils.extractToken(storedKeyValue);

        baseRequest = new BaseRequest();
        HttpRequest request = baseRequest.prepareDELETERequest(DELETE_CATEGORY + id, token);
        client = HttpClient.newHttpClient();
        baseRequest.sendRequest(client, request);
    }
}
