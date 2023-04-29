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
    public String weather, windDirection;
    public int pressure;
    public int windBFT;
    public double windChill, tempC, tempF;

    public Station(String name) {
        this.name = name;
    }

}
