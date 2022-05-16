import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class WebAPIDemo {

    public static void main(String[] args) {
        String APIkey = "02924c4f8fbbd88c404cc1e4942fb5f1";

        String urlNowPlaying = "https://api.themoviedb.org/3/movie/popular?api_key=" + APIkey + "&language=en-US";
        makeAPICall(urlNowPlaying);

        System.out.print("Which movie (enter ID)? ");
        Scanner s = new Scanner(System.in);
        String movieID = s.nextLine();

        String movieURL = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + APIkey + "&language=en-US";
        makeAPICall(movieURL);
    }

    public static void makeAPICall(String url) {
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.headers());
            System.out.println(response.body());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}