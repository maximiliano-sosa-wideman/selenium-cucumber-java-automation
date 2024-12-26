package hellocucumber.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class BaseRequest {

    public HttpRequest prepareRequest(String url, String requestBody){

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer" + token)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
    }

    public HttpRequest prepareRequest(String url, String requestBody, String authToken){

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer" + authToken)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
    }

    public HttpResponse<String> sendRequest(HttpClient client, HttpRequest request){
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("No se pudo hacer el login request", e);
        }
    }
}
