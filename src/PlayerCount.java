import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;


public class PlayerCount {

    public static void main(String[] args) {
        String APIkey = "";

//        System.out.print("What is your Minecraft Username? ");
          Scanner s = new Scanner(System.in);
          String name = s.nextLine();

//        String getUUIDUrl = "https://api.mojang.com/users/profiles/minecraft/" + name;
//        String UUIDJSON = makeAPICall(getUUIDUrl);
//        String UUID = UUIDJSON.substring(UUIDJSON.indexOf("\"id\":\"") + 6, UUIDJSON.length()-2);

        String getModeURL = "";
        String gameModeJSON = makeAPICall(getModeURL);

        ArrayList<GameMode> gameMode = new ArrayList<GameMode>();
        parseJSON(gameModeJSON, gameMode);
//        while (profileJSON.indexOf("profile_id") != 0)
//        {
//            String id = "";
//            String cuteName = "";
//            for (int i = 0; i < 50; i++)
//            {
//                try {
//                    if (profileJSON.substring(profileJSON.indexOf("\"profile_id\":") + 15 + i).equals("\""))
//                    {
//                        i = 60;
//                    }
//                    id += profileJSON.substring(profileJSON.indexOf("\"profile_id\":") + 14 + i, profileJSON.indexOf("\"profile_id\":") + 15 + i);
//                } catch (Exception e) {
//                    i = 60;
//                }
//            }
//            for (int i = 0; i < 20; i++)
//            {
//                try {
//                    if (profileJSON.substring(profileJSON.indexOf("\"cute_name\"") + 14 + i).equals("\""))
//                    {
//                        i = 50;
//                    }
//                    cuteName += profileJSON.substring(profileJSON.indexOf("\"cute_name\"") + 13 + i, profileJSON.indexOf("\"cute_name\"") + 14 + i);
//                } catch (Exception e) {
//                    i = 50;
//                }
//            }
//
//            Profile profile = new Profile(cuteName, id);
//            profiles.add(profile);
//            profileJSON = profileJSON.substring(profileJSON.indexOf(cuteName) + cuteName.length());
//        }

        System.out.println("GameMode: ");
        for (GameMode g : gameMode)
        {
            System.out.println(g.getName());
        }
        System.out.print("\nChoose a gameMode! ");
        String choice = s.nextLine();
        for (GameMode g : gameMode)
        {
            if (g.getName().equalsIgnoreCase(choice))
            {
                String getChosenProfileURL = "https://api.hypixel.net/counts";
                String chosenProfileJSON = makeAPICall(getChosenProfileURL);
                JSONObject json = new JSONObject(chosenProfileJSON); // Convert text to object
                System.out.println(json.toString(4)); // Print it with specified indentation
            }
        }
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

    public static void parseJSON(String json, ArrayList<GameMode> list)
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray gamemodeList = jsonObj.getJSONArray("games");

        for (int i = 0; i < gamemodeList.length(); i++)
        {
            JSONObject obj = gamemodeList.getJSONObject(i);
            String name = obj.getString("GAME_TYPE");
            list.add(new GameMode(name));
        }
    }
}