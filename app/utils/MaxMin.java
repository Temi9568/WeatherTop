package utils;

import models.Reading;
import models.Station;

import java.util.ArrayList;

public class MaxMin {
    public static double getMax(Station st, String type) {
        ArrayList<Double> temps = new ArrayList<Double>();
        double maxValue = 0;

        for (Reading reading: st.readings) {
            if (type.equals("w")) {
                maxValue = Math.max(maxValue, reading.windSpeed);
            } else if (type.equals("t")) {
                maxValue = Math.max(maxValue, reading.temperature);
            } else {
                maxValue = Math.max(maxValue, reading.pressure);
            }
        }

        return maxValue;

    }

    public static double getMin(Station st, String type) {
        ArrayList<Double> temps = new ArrayList<Double>();
        double minValue = 100000;

        for (Reading reading: st.readings) {
            if (type.equals("w")) {
                minValue = Math.min(minValue, reading.windSpeed);
            } else if (type.equals("t")) {
                minValue = Math.min(minValue, reading.temperature);
            } else {
                minValue = Math.min(minValue, reading.pressure);
            }
        }

        return minValue;

    }
}
