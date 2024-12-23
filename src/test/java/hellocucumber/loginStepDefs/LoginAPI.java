package hellocucumber.loginStepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LoginAPI {

    private static final String LOGIN_EP_URL = "https://api.club-administration.qa.qubika.com/api/auth/login";

    @Given("a user wanting to log in by API")
    public void aUserWantingToLogIn() {
        System.out.println("Pasa por primer paso");
    }

    @When("a valid email and password is passed to the login endpoint")
    public void aValidEmailAndPasswordIsPassedToTheLoginEndpoint() {
        System.out.println("inicio de segundo paso");

        String requestBody = new JSONObject()
                .put("email", System.getenv("VALID_EMAIL"))
                .put("password", System.getenv("VALID_PASSWORD"))
                .toString();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_EP_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // You can handle the response here later
            if(!response.body().isEmpty() ){
                System.out.println("vino el token: " + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo hacer el login request", e);
        }
    }

    @Then("the user is logged in")
    public void theUserIsLoggedIn() {
        System.out.println("pasa por el ultimo paso");
    }
}

