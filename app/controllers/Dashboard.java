package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Controller {


    public static void index() {
        if (!session.contains("logged_in_Memberid")) { redirect("/login");}
        Logger.info("Rendering Admin");
        Member member = Account.getCurrentMember();

        for (Station station: member.stations) {
            if (station.readings.size() >= 1) {
                Reading r = station.readings.get(station.readings.size() - 1);
                station.weather = getWeather(r.code);
                station.tempC = r.temperature;
                station.tempF = getTempF(r.temperature);
                station.windBFT = getWindBFT(r.windSpeed);
                station.windDirection = getWindDirection();
                station.pressure = r.pressure;
            }
        }
        member.save();
        for (Station station: member.stations) {
            System.out.println(station.name);
        }

        render("dashboard.html", member);
    }

    public static void addReading(Long id, int code, float temperature, double windSpeed,  int windDirection, int pressure) {
        Station station = Station.findById(id);
        Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
        station.readings.add(reading);
        station.save();
        redirect("/dashboard");

    }

    public static void deleteReading(Long stationId, Long readingId) {
        Station station = Station.findById(stationId);
        Reading reading = Reading.findById(readingId);
        station.readings.remove(reading);
        station.save();
        redirect("/dashboard");
    }

    public static void addStation(String name) {
        Member member = Account.getCurrentMember();
        Station station = new Station(name);
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



    public static String getWeather(int code) {
        String weather;
        switch(code) {
            case 100: weather = "Clear";
            break;
            case 200: weather = "Patial Clouds";
            break;
            case 300: weather = "Cloudy";
            break;
            case 400: weather = "Light Showers";
            break;
            case 500: weather = "Heavy Showers";
            break;
            case 600: weather = "Rain";
            break;
            case 700: weather = "Snow";
            break;
            case 800: weather = "Thunder";
            break;
            default: weather = "N/A";
            break;
        }
        return weather;
    }

    public static double getTempF(double tempCelcius) {
        return tempCelcius * (9.0/5.0) + 32;
    }

    public static int getWindBFT(double windSpeed) {
        List<int[]> conversions = new ArrayList<int[]>();
        conversions.add(new int[] {0, 1, 0});
        conversions.add(new int[] {1, 5, 1});
        conversions.add(new int[] {6, 11, 2});
        conversions.add(new int[] {12, 19, 3});
        conversions.add(new int[] {20, 28, 4});
        conversions.add(new int[] {29, 38, 5});
        conversions.add(new int[] {39, 49, 6});
        conversions.add(new int[] {50, 61, 7});
        conversions.add(new int[] {63, 74, 8});
        conversions.add(new int[] {75, 88, 9});
        conversions.add(new int[] {89, 102, 10});
        conversions.add(new int[] {103, 117, 12});

        for (int[] c: conversions) {
            if (windSpeed > c[0] && windSpeed <= c[1]) {
                return c[2];
            }
        }
        return 0;
    }

    public static String getWindDirection(){
        return "N";
    }
    public static double getWindChill(double tempCelsius, double windSpeed) {
        return 13.12 + 0.6215*tempCelsius - 11.37*Math.pow(windSpeed, 0.16) + (0.3965*tempCelsius)*Math.pow(windSpeed, 0.16);
    }


}
