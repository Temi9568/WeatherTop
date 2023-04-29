package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Reading extends Model {

    public int code;
    public float temperature;
    public double windSpeed;
    public int pressure;
    public int windDirection;

    public Reading(int code, float temperature, double windSpeed,  int windDirection, int pressure) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;

    }
}
