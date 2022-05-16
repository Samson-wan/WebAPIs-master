public class WeatherAPI {

    private WeatherNetworking networker;

    public WeatherAPI() {
        this.networker = new WeatherNetworking();
    }

    public void currentWeather(String zipCode, int days)
    {
        String response = networker.makeAPICallForForecast(zipCode, days);
        networker.parseForecast(response);
    }
}