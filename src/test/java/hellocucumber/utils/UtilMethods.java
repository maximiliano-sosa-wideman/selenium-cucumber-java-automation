package hellocucumber.utils;

import org.json.JSONObject;

import java.net.http.HttpResponse;

public class UtilMethods {

    public String getTokenFromLogin(HttpResponse<String> response){
        JSONObject jsonResponse = new JSONObject(response.body());
        return jsonResponse.get("token").toString();
    }
}
