package controllers;

import models.*;
import play.mvc.Controller;

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
        redirect("/dashboard");

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
        redirect("/dashboard");
    }

}
