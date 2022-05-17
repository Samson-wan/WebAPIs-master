import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;


public class HypixelAPI {

    public static void main(String[] args) {
        String APIkey = "65390b16-ad31-4c9a-877b-64cf031212bc";

        System.out.print("What is your Minecraft Username? ");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();

        String getUUIDUrl = "https://api.mojang.com/users/profiles/minecraft/" + name;
        String UUIDJSON = makeAPICall(getUUIDUrl);
        String UUID = UUIDJSON.substring(UUIDJSON.indexOf("\"id\":\"") + 6, UUIDJSON.length()-2);

        String getProfileURL = "https://api.hypixel.net/skyblock/profiles?key=" + APIkey + "&uuid=" + UUID;
        String profileJSON = makeAPICall(getProfileURL);

        ArrayList<Profile> profiles = new ArrayList<Profile>();
        parseJSON(profileJSON, profiles);
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

        System.out.println("Profiles: ");
        for (Profile p : profiles)
        {
            System.out.println(p.getName());
        }
        System.out.print("\nChoose a profile! ");
        String choice = s.nextLine();
        for (Profile p : profiles)
        {
            if (p.getName().equalsIgnoreCase(choice))
            {
                String getChosenProfileURL = "https://api.hypixel.net/skyblock/profile?key=" + APIkey + "&profile=" + p.getUUID();
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

    public static void parseJSON(String json, ArrayList<Profile> list)
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray profileList = jsonObj.getJSONArray("profiles");

        for (int i = 0; i < profileList.length(); i++)
        {
            JSONObject obj = profileList.getJSONObject(i);
            String uuid = obj.getString("profile_id");
            String cuteName = obj.getString("cute_name");
            
            list.add(new Profile(cuteName, uuid));
        }
    }
}