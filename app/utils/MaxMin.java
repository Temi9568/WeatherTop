package utils;

import models.Reading;
import models.Station;

import java.util.ArrayList;

/**
 * Utility class for calculating maximum and minimum values.
 */
public class MaxMin {
    /**
     * Calculates maximum value of a specific type (wind speed, temperature, pressure)
     *
     * @param st   - Station object
     * @param type - Type of value to calculate (w for wind speed, t for temp, p for pressure)
     * @return - maximum value
     */
    public static double getMax(Station st, String type) {
        ArrayList<Double> temps = new ArrayList<Double>();
        double maxValue = 0;

        for (Reading reading : st.readings) {
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
        /**
         * Calculates minimum value of a specific type (wind speed, temperature, pressure)
         * @param st - Station object
         * @param type - Type of value to calculate (w for wind speed, t for temp, p for pressure)
         * @return - minimum value
         */
        ArrayList<Double> temps = new ArrayList<Double>();
        double minValue = 100000;

        for (Reading reading : st.readings) {
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
