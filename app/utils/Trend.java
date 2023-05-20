package utils;

import models.Station;

public class Trend {
    public static String getStationTempTrend(Station station) {
        if (station.readings.size() < 3) {return "";}
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

    public static String getStationWindTrend(Station station) {
        if (station.readings.size() < 3) {return "";}
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

    public static String getStationPressureTrend(Station station) {
        if (station.readings.size() < 3) {return "";}
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
