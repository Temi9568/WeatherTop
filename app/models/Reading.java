package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Reading extends Model {

    public int code;
    public float temperature;
    public double windSpeed;
    public int pressure;
    public int windDirection;
    public String date;

    public Reading(int code, float temperature, double windSpeed,  int windDirection, int pressure, String date) {
        System.out.println("dshjsdjhsdweiuewiuewuweiuwe");
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.date = getFormattedDate(date);

    }

    public static String getFormattedDate(String dateString) {
        SimpleDateFormat dform = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateObject = dform.parse(dateString);
            System.out.println(dform.format(dateObject));
            return dform.format(dateObject);
        } catch (ParseException e) {
            System.out.println(e);
            return dateString;
        }
    }

    public static String getFormattedDate() {
        SimpleDateFormat dform = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateObject = new Date();
        return dform.format(dateObject);
    }
}
