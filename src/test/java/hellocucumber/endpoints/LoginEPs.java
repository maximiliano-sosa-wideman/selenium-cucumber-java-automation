package hellocucumber.endpoints;

import hellocucumber.utils.BaseRequest;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginEPs {

    HttpClient client;
    BaseRequest baseRequest;

    private static final String LOGIN_EP_URL = "https://api.club-administration.qa.qubika.com/api/auth/login";


    public HttpResponse<String> login(String email, String password){
        // Setups the request body
        String requestBody = new JSONObject()
                .put("email", email)
                .put("password", password)
                .toString();

        // prepares the request with the supplied information.
        baseRequest = new BaseRequest();
        HttpRequest request = baseRequest.prepareRequest(LOGIN_EP_URL, requestBody);

        // instantiates a new client and sends the request
        client = HttpClient.newHttpClient();
        return baseRequest.sendRequest(client, request);
    }

//    public HttpResponse<String> logout(){
//         WIP
//    }
}
