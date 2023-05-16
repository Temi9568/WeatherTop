package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Reading extends Model {

    public int code;
    public double temperature;
    public double windSpeed;
    public int pressure;
    public int windDirection;
    public Date date;

    public Reading(int code, double temperature, double windSpeed,  int windDirection, int pressure, Date date) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.date = date;

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
