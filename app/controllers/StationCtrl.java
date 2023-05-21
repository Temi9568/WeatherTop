package controllers;

import models.*;
import play.mvc.Controller;
import utils.Conversion;
import utils.MaxMin;
import utils.Trend;

import java.util.Date;

/**
 * Controller class for handling POST requests related to Station and Reading objects.
 */
public class StationCtrl extends Controller {
    /**
     * Renders "station-dashboard.html" for a singular station view
     *
     * @param id - station's id (Long)
     */
    public static void index(Long id) {
        boolean isDashboard = false;
        if (!session.contains("logged_in_Memberid")) {
            redirect("/login");
        }
        Station station = Station.findById(id);
        if (station != null && station.readings.size() >= 1) { // If no readings then unable to set additional station fields.
            Reading r = station.readings.get(station.readings.size() - 1);  // get most recent reading
            station = StationCtrl.setStationExtraFields(station, r);    // really shouldn't be altering LCV here
        }
        render("station-dashboard.html", station, isDashboard);
    }

    /**
     * Handles POST request to add a station.
     *
     * @param name - station name (String)
     * @param lat  - station latitude (double)
     * @param lng  - station longitude (double)
     */
    public static void addStation(String name, double lat, double lng) {
        Member member = MemberCtrl.getCurrentMember();
        Station station = new Station(name, lat, lng);
        member.stations.add(station);
        member.save();
        redirect("/dashboard");
    }

    /**
     * Handles POST request to delete a station.
     *
     * @param id - station's id (Long)
     */
    public static void deleteStation(Long id) {
        Member member = MemberCtrl.getCurrentMember();
        Station station = Station.findById(id);
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect("/dashboard");
    }

    /**
     * Handles POST request to add a reading
     *
     * @param id            - reading's id (Long)
     * @param code          - reading's temp code (int)
     * @param temperature   - reading's temperature (float)
     * @param windSpeed     - reading's wind speed (double)
     * @param windDirection - reading's wind direction (int)
     * @param pressure      - reading's pressure (int)
     */
    public static void addReading(Long id, int code, float temperature, double windSpeed, int windDirection, int pressure) {
        Station station = Station.findById(id);
        Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure, new Date());
        station.readings.add(reading);
        station.save();
        redirect("/station/" + String.valueOf(station.id));   // redirect back to same station view user was on

    }

    /**
     * Handles POST request to delete a reading
     *
     * @param stationId - station's id (Long)
     * @param readingId - reading's id (Long)
     */
    public static void deleteReading(Long stationId, Long readingId) {
        Member member = MemberCtrl.getCurrentMember();
        Reading reading = Reading.findById(readingId);
        Station station = Station.findById(stationId);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/station/" + String.valueOf(station.id));   // redirect back to same station view user was on
    }

    /**
     * Sets a station's extra fields/params that were not set in the consturctor
     *
     * @param station - Station object
     * @param r       - Last reading (Reading)
     * @return Station object
     */
    public static Station setStationExtraFields(Station station, Reading r) {

        // Weather pane
        station.weather = Conversion.convertCodeToWeather(r.code);

        // Temp pane
        station.tempC = r.temperature;  // tempC of station will be stations most recent readings temp
        station.tempF = Conversion.convertToTempF(r.temperature);   // convert tempC to tempF
        station.max = MaxMin.getMax(station, "t");  // get max temp (t for temp)
        station.min = MaxMin.getMin(station, "t");  // get min temp (t for temp)
        station.tempTrend = Trend.getStationTempTrend(station); // sets station temp trend

        // Wind pane
        station.windBFT = Conversion.convertToWindBFT(r.windSpeed); // sets station wind bft.
        station.windDirection = Conversion.convertToWindDirectionString(r.windDirection);   // literal
        station.windChill = Conversion.convertToWindChill(r.temperature, r.windSpeed);  // literal
        station.windMax = MaxMin.getMax(station, "w");  // get max wind (w for wind)
        station.windMin = MaxMin.getMin(station, "w");  // get min temp (w for wind)
        station.windSpeed = r.windSpeed;    // wind speed of station will be stations most recent readings ws
        station.windTrend = Trend.getStationWindTrend(station); // sets station wind trend

        // Pressure pane
        station.pressure = r.pressure;  // pressure of station will be stations most recent readings pressure
        station.pressureMax = MaxMin.getMax(station, "p");  // get max pressure (p for pressure)
        station.pressureMin = MaxMin.getMin(station, "p");   // get min pressure (p for pressure)
        station.pressureTrend = Trend.getStationPressureTrend(station); // sets station pressure trend

        return station;
    }
}
