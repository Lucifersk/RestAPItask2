import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class Apitask2{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            //for look good user Friendly
            System.out.println("╔═══════════════════════════════════════════════════════════╗");
            System.out.println("       WELCOME TO MY THE WEATHER FORECAST API TASK :         ");
            System.out.println("╚═══════════════════════════════════════════════════════════╝");

            // Get city name from user
            while (true) {
                System.out.print("Enter City Name: ");
                String city = scanner.nextLine();

                //  Call API to get coordinates
                String geoUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + URLEncoder.encode(city, "UTF-8");
                URL geoApi = new URL(geoUrl);
                HttpURLConnection geoConn = (HttpURLConnection) geoApi.openConnection();
                geoConn.setRequestMethod("GET");
                geoConn.setRequestProperty("User-Agent", "Mozilla/5.0");

                BufferedReader geoReader = new BufferedReader(new InputStreamReader(geoConn.getInputStream()));
                StringBuilder geoResponse = new StringBuilder();
                String line;
                while ((line = geoReader.readLine()) != null) {
                    geoResponse.append(line);
                }
                geoReader.close();

                JSONArray locationArray = new JSONArray(geoResponse.toString());
                if (locationArray.length() == 0) {
                    System.out.println("╔════════════════════╗");
                    System.out.println("    City not found!   ");
                    System.out.println("╚════════════════════╝");
                    return;
                }

                JSONObject location = locationArray.getJSONObject(0);
                double latitude = location.getDouble("lat");
                double longitude = location.getDouble("lon");

                // Use coordinates to fetch weather
                String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                        "&longitude=" + longitude + "&current_weather=true";

                URL url = new URL(weatherUrl);
                HttpURLConnection weatherConn = (HttpURLConnection) url.openConnection();
                weatherConn.setRequestMethod("GET");

                int status = weatherConn.getResponseCode();
                if (status != 200) {
                    System.out.println("Failed to fetch weather data. HTTP Code: " + status);
                    return;
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(weatherConn.getInputStream()));
                StringBuilder weatherResponse = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    weatherResponse.append(line);
                }
                reader.close();

                JSONObject jsonResponse = new JSONObject(weatherResponse.toString());
                JSONObject currentWeather = jsonResponse.getJSONObject("current_weather");

                // Format the time
                String timeRaw = currentWeather.getString("time");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date date = inputFormat.parse(timeRaw);

                SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
                outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // or use your local time zone

                String formattedTime = outputFormat.format(date);

                // Display output
                System.out.println("\nWeather in " + city + ":");
                System.out.println("═════════════════════════════════════════════════════════════════════");
                System.out.println("Temperature: " + currentWeather.getDouble("temperature") + " °C");
                System.out.println("Wind Speed: " + currentWeather.getDouble("windspeed") + " km/h");
                System.out.println("Time: " + formattedTime);
                System.out.println("═════════════════════════════════════════════════════════════════════");

                // Ask if the user wants to continue
                System.out.print("\nDo you want to check another city? (yes/no): ");
                String answer = scanner.nextLine().trim().toLowerCase();//Converts text to all lowercase & removes spaces before and after the input text.
                if (answer.equals("no") || answer.equals("n")) {
                    System.out.println("\nThank you for using the Weather Forecast App. Stay safe!");
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
