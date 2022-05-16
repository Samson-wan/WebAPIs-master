import java.util.Scanner;

public class WeatherRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter zip code for weather: ");
        String zip = s.nextLine();
        System.out.print("How many days? ");
        int days = s.nextInt();

        WeatherAPI api = new WeatherAPI();
        api.currentWeather(zip, days);
    }
}
