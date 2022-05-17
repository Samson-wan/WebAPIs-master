import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


public class ActiveAuctions {
    public static void main(String[] args) {
//        String APIkey = "65390b16-ad31-4c9a-877b-64cf031212bc";
//
//        System.out.print("What is your Minecraft Username? ");
//        Scanner s = new Scanner(System.in);
//        String name = s.nextLine();
//
//        String getUUIDUrl = "https://api.mojang.com/users/profiles/minecraft/" + name;
//        String UUIDJSON = makeAPICall(getUUIDUrl);
//        String UUID = UUIDJSON.substring(UUIDJSON.indexOf("\"id\":\"") + 6, UUIDJSON.length()-2);

        String getAunctionsURL = "https://api.hypixel.net/skyblock/auctions";
        String auctionJSON = makeAPICall(getAunctionsURL);
        System.out.println("Aunctions: ");
        String chosenProfileJSON = makeAPICall(getAunctionsURL);
        JSONObject json = new JSONObject(chosenProfileJSON); // Convert text to object
        System.out.println(json.toString(4)); // Print it with specified indentation

    }

    public static String makeAPICall(String url) {
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //System.out.println(response.body());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

//    public static void parseJSON(String json, ArrayList<Item> list)
//    {
//        JSONObject jsonObj = new JSONObject(json);
//        JSONArray auctionList = jsonObj.getJSONArray("auctions");
//
//        for (int i = 0; i < auctionList.length(); i++)
//        {
//            JSONObject obj = auctionList.getJSONObject(i);
//            String name = obj.getString("item_name");
//            list.add(new Item((name)));
//        }
//    }
}