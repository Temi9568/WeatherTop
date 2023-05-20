package utils;

import models.Station;

/**
 * Utility class for calculating weather trends for a given station.
 */
public class Trend {
    /**
     * Calculates the temperature trend for a given station (station must have at least 3 readings)
     *
     * @param station - Station object
     * @return - temperature trend (up for increasing or down for decreasing)
     */
    public static String getStationTempTrend(Station station) {
        if (station.readings.size() < 3) {
            return "";
        }
        double temp3 = station.readings.get(2).temperature;
        double temp2 = station.readings.get(1).temperature;
        double temp1 = station.readings.get(0).temperature;
        if (temp3 > temp2 && temp2 > temp1) {
            return "up";
        } else if (temp3 < temp2 && temp2 < temp1) {
            return "down";
        }
        return "";
    }

    /**
     * Calculates the wind speed trend for a given station (station must have at least 3 readings)
     *
     * @param station - Station object
     * @return - wind speed trend (up for increasing or down for decreasing)
     */
    public static String getStationWindTrend(Station station) {
        if (station.readings.size() < 3) {
            return "";
        }
        double temp3 = station.readings.get(2).windSpeed;
        double temp2 = station.readings.get(1).windSpeed;
        double temp1 = station.readings.get(0).windSpeed;
        if (temp3 > temp2 && temp2 > temp1) {
            return "up";
        } else if (temp3 < temp2 && temp2 < temp1) {
            return "down";
        }
        return "";
    }

    /**
     * Calculates the pressure trend for a given station (station must have at least 3 readings)
     *
     * @param station - Station object
     * @return - pressure trend (up for increasing or down for decreasing)
     */
    public static String getStationPressureTrend(Station station) {
        if (station.readings.size() < 3) {
            return "";
        }
        double temp3 = station.readings.get(2).pressure;
        double temp2 = station.readings.get(1).pressure;
        double temp1 = station.readings.get(0).pressure;
        if (temp3 > temp2 && temp2 > temp1) {
            return "up";
        } else if (temp3 < temp2 && temp2 < temp1) {
            return "down";
        }
        return "";
    }

}
