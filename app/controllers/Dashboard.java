package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.DateFormat;
import java.util.Date;

public class Dashboard extends Controller {


    public static void index() {
        if (!session.contains("logged_in_Memberid")) { redirect("/login");}
        Logger.info("Rendering Admin");
        Member member = Account.getCurrentMember();

        for (Station station: member.stations) {
            if (station.readings.size() >= 1) {
                Reading r = station.readings.get(station.readings.size() - 1);

                // Temp pane
                station.weather = getWeather(r.code);

                // Weather pane
                station.tempC = r.temperature;
                station.tempF = getTempF(r.temperature);
                station.max = getMax(station, "t");
                station.min = getMin(station, "t");

                // Wind pane
                station.windBFT = getWindBFT(r.windSpeed);
                station.windDirection = getWindDirection(r.windDirection);
                station.windChill = getWindChill(r.temperature, r.windSpeed);
                station.windMax = getMax(station, "w");
                station.windMin = getMin(station, "w");

                // Pressure pane
                station.pressure = r.pressure;
                station.pressureMax = getMax(station, "p");
                station.pressureMin = getMin(station, "p");
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
        String date = Reading.getFormattedDate();
        Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure, date);
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
            default: weather = "Varied";
            break;
        }
        return weather;
    }

    public static double getTempF(double tempCelcius) {
        return tempCelcius * (9.0/5.0) + 32;
    }

    public static double getMax(Station st, String type) {
        ArrayList<Double> temps = new ArrayList<Double>();
        double maxValue = 0;

        for (Reading reading: st.readings) {
            if (type.equals("w")) {
                maxValue = Math.max(maxValue, reading.windSpeed);
            } else if (type.equals("t")) {
                maxValue = Math.max(maxValue, reading.temperature);
            } else {
                maxValue = Math.max(maxValue, reading.pressure);
            }
        }

        return maxValue;

    }

    public static double getMin(Station st, String type) {
        ArrayList<Double> temps = new ArrayList<Double>();
        double minValue = 100000;

        for (Reading reading: st.readings) {
            if (type.equals("w")) {
                minValue = Math.min(minValue, reading.windSpeed);
            } else if (type.equals("t")) {
                minValue = Math.min(minValue, reading.temperature);
            } else {
                minValue = Math.min(minValue, reading.pressure);
            }
        }

        return minValue;

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

    public static String getWindDirection(double windDirection){
        String windD;
        HashMap<String, String> windMap = new HashMap<>();
        windMap.put("N", "North");
        windMap.put("NNE", "North North East");
        windMap.put("NE", "North East");
        windMap.put("ENE", "East North East");
        windMap.put("E", "East");
        windMap.put("ESE", "East South East");
        windMap.put("SE", "South East");
        windMap.put("SSE", "South South East");
        windMap.put("S", "South");
        windMap.put("SSW", "South South West");
        windMap.put("SW", "South West");
        windMap.put("WSW", "West South West");
        windMap.put("W", "West");
        windMap.put("WNW", "West North West");
        windMap.put("NW", "North West");
        windMap.put("NNW", "North North West");
        windMap.put("Unknown", "N/A");

        String[] directions = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
        int index = (int) ((windDirection / 22.5) + 0.5) % 16;    // Casting or else will get err as return is long
        return windMap.get(directions[index]);
    }


    public static double getWindChill(double tempCelsius, double windSpeed) {
        double value = 13.12 + 0.6215*tempCelsius - 11.37*Math.pow(windSpeed, 0.16) + (0.3965*tempCelsius)*Math.pow(windSpeed, 0.16);
        return Double.parseDouble(String.format("%.1f",  value));
    }


}
