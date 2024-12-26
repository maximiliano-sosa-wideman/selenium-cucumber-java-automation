package hellocucumber.endpoints;

import hellocucumber.utils.BaseRequest;
import org.json.JSONObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CategoryEPs {

    HttpClient client;
    BaseRequest baseRequest;

    private static final String CREATE_CATEGORY_EP = "https://api.club-administration.qa.qubika.com/api/category-type/create";

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
}
