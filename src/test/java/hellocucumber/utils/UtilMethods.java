package hellocucumber.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;

public class UtilMethods {

    // Get the token from the login to use it to access other pages more quickly
    public String getTokenFromLogin(HttpResponse<String> response){
        JSONObject jsonResponse = new JSONObject(response.body());
        return jsonResponse.get("token").toString();
    }

    public String extractToken(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getString("token");
    }

    // Return the last category ID created with the Category Test
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
}

