package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Utility class for weather data conversions.
 */
public class Conversion {
    /**
     * Converts/Matches weather code to corresponding weather description
     *
     * @param code - weather code (int)
     * @return - Weather description
     */
    public static String convertCodeToWeather(int code) {
        String weather;
        switch (code) {
            case 100:
                weather = "Clear";
                break;
            case 200:
                weather = "Patial Clouds";
                break;
            case 300:
                weather = "Cloudy";
                break;
            case 400:
                weather = "Light Showers";
                break;
            case 500:
                weather = "Heavy Showers";
                break;
            case 600:
                weather = "Rain";
                break;
            case 700:
                weather = "Snow";
                break;
            case 800:
                weather = "Thunder";
                break;
            default:
                weather = "Varied";
                break;
        }
        return weather;
    }

    /**
     * Converts temp from Celsius to Fahrenheit
     *
     * @param tempCelcius - temp in Celsius (double)
     * @return - Celsius in Fahrenheit
     */
    public static double convertToTempF(double tempCelcius) {
        return tempCelcius * (9.0 / 5.0) + 32;
    }


    /**
     * Converts windSpeed to Beaufort scale
     *
     * @param windSpeed - wind speed (double)
     * @return - Beaufort scale value
     */
    public static int convertToWindBFT(double windSpeed) {
        List<int[]> conversions = new ArrayList<int[]>();
        conversions.add(new int[]{0, 1, 0});
        conversions.add(new int[]{1, 5, 1});
        conversions.add(new int[]{6, 11, 2});
        conversions.add(new int[]{12, 19, 3});
        conversions.add(new int[]{20, 28, 4});
        conversions.add(new int[]{29, 38, 5});
        conversions.add(new int[]{39, 49, 6});
        conversions.add(new int[]{50, 61, 7});
        conversions.add(new int[]{63, 74, 8});
        conversions.add(new int[]{75, 88, 9});
        conversions.add(new int[]{89, 102, 10});
        conversions.add(new int[]{103, 117, 12});

        for (int[] c : conversions) {
            if (windSpeed > c[0] && windSpeed <= c[1]) {
                return c[2];
            }
        }
        return 0;
    }

    /**
     * Converts wind direction to corresponding string match.
     *
     * @param windDirection - wind direction (double)
     * @return - string match
     */
    public static String convertToWindDirectionString(double windDirection) {
        String windD;
        HashMap<String, String> windMap = new HashMap<>();
        windMap.put("N", "North");
        windMap.put("NNE", "North North East");
        windMap.put("NE", "North East");
        windMap.put("ENE", "East North East");
        windMap.put("E", "East");
        windMap.put("ESE", "East South East");
        windMap.put("SE", "South East");
        windMap.put("SSE", "South South East");
        windMap.put("S", "South");
        windMap.put("SSW", "South South West");
        windMap.put("SW", "South West");
        windMap.put("WSW", "West South West");
        windMap.put("W", "West");
        windMap.put("WNW", "West North West");
        windMap.put("NW", "North West");
        windMap.put("NNW", "North North West");
        windMap.put("Unknown", "N/A");

        String[] directions = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
        int index = (int) ((windDirection / 22.5) + 0.5) % 16;    // Casting or else will get err as return is long
        return windMap.get(directions[index]);
    }

    /**
     * Converts wind speed & temp (Celsius) to wind chill
     *
     * @param tempCelsius - temp in Celsius (double)
     * @param windSpeed   - wind speed (double)
     * @return - returns wind chill
     */
    public static double convertToWindChill(double tempCelsius, double windSpeed) {
        double value = 13.12 + 0.6215 * tempCelsius - 11.37 * Math.pow(windSpeed, 0.16) + (0.3965 * tempCelsius) * Math.pow(windSpeed, 0.16);
        return Double.parseDouble(String.format("%.1f", value));
    }
}
