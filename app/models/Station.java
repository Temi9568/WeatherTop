package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Station object for a new weather station.
 */
@Entity
public class Station extends Model {
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();   // Station can have many readings (one to many relationship)
    // Extra params below will be set in Dashboard controller
    public String name;
    public String weather, windDirection, tempTrend, windTrend, pressureTrend;
    public int pressure;
    public int windBFT;
    public double windChill, tempC, tempF, lat, lng, max, min, windMax, windMin, windSpeed, pressureMax, pressureMin;

    /**
     * Create a Station object (Constructor)
     *
     * @param name - station's name (String)
     * @param lat  - station's latitude (double)
     * @param lng  - station's longitude (double)
     */
    public Station(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

}
