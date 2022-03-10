

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClient {

    public static void main() throws IOException, InterruptedException {

//        var values = new HashMap<String, String>() {{
//            put("name", "John Doe");
//            put ("occupation", "gardener");
//        }};

        String jsonString = "{\"time\":218, \"liftID\":22, \"waitTime\":4}";

//        var objectMapper = new ObjectMapper();
//        String requestBody = objectMapper
//                .writeValueAsString(jsonString);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/BSDS/skiers/3/seasons/2019/days/3/skiers/111"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}