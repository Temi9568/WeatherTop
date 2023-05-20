package controllers;

import models.*;
import play.mvc.Controller;

import java.util.Date;


public class StationCtrl extends Controller {

    public static void index(Long id) {
        if (!session.contains("logged_in_Memberid")) { redirect("/login");}
        Station station = Station.findById(id);
        render("station-dashboard.html", station);
    }

    // ____________________________________________  POST REQUESTS ____________________________________________
    public static void addStation(String name, double lat, double lng) {
        Member member = Account.getCurrentMember();
        Station station = new Station(name, lat, lng);
        member.stations.add(station);
        member.save();
        redirect("/dashboard");
    }

    public static void deleteStation(Long id) {
        Member member = Account.getCurrentMember();
        Station station = Station.findById(id);
        member.stations.remove(station);
        member.save();
        station.delete();
        redirect("/dashboard");
    }

    public static void addReading(Long id, int code, float temperature, double windSpeed,  int windDirection, int pressure) {
        Station station = Station.findById(id);
        String date = Reading.getFormattedDate();
        Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure, new Date());
        station.readings.add(reading);
        station.save();
        redirect("/dashboard");

    }

    public static void deleteReading(Long stationId, Long readingId) {
        Member member = Account.getCurrentMember();
        Reading reading = Reading.findById(readingId);
        Station station = Station.findById(stationId);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        redirect("/dashboard");
    }

}
