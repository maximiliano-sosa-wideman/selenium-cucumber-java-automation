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
        JSONArray jsonArray = new JSONArray(jsonString);

        // Get the last non-null element that has an id
        String lastId = null;
        for (int i = jsonArray.length() - 1; i >= 0; i--) {
            JSONObject item = jsonArray.getJSONObject(i);
            if (item.has("id") && !item.isNull("id")) {
                lastId = item.getString("id");
                break;
            }
        }
        return lastId;
    }
    // duplicated code that needs to be better implemented
    public String getLastCategoryNameFromList(String jsonString){
//        System.out.println(jsonString);
        JSONArray jsonArray = new JSONArray(jsonString);

        // Get the last non-null element that has an id
        String categoryName = null;
        for (int i = jsonArray.length() - 1; i >= 0; i--) {
            JSONObject item = jsonArray.getJSONObject(i);
            if (item.has("name") && !item.isNull("name")) {
                categoryName = item.getString("name");
                break;
            }
        }
        return categoryName;
    }
}
