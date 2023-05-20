package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Reading object for a weather's reading
 */
@Entity
public class Reading extends Model {

    public int code;
    public double temperature;
    public double windSpeed;
    public int pressure;
    public int windDirection;
    public Date date;

    /**
     * Creates a new Reading object (Constructor)
     *
     * @param code          - weather code (int)
     * @param temperature   - weather temp in Celcius (double)
     * @param windSpeed     - weather wind speed (double)
     * @param windDirection - weather wind direction (int)
     * @param pressure      - weather pressure (int)
     * @param date          - date and time of reading (Date)
     */
    public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure, Date date) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.date = date;

    }

    /**
     * Gets the current date and time as a formatted string.
     *
     * @return The formatted date string.
     */
    public static String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateObject = new Date();
        return dateFormat.format(dateObject);
    }
}
