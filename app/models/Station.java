package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station extends Model {
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public String name;
    public String weather, windDirection, tempTrend,  windTrend,  pressureTrend;
    public int pressure;
    public int windBFT;
    public double windChill, tempC, tempF, lat, lng, max, min, windMax, windMin, windSpeed, pressureMax, pressureMin;

    public Station(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

}
