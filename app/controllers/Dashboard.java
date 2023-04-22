package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Controller {


    public static void index() {
        Logger.info("Rendering Admin");
        List<Station> stations = Station.findAll();
        for (Station station: stations) {
            station.weather = getWeather(station.readings.get(station.readings.size() -1).code);
            station.temp = getTemp(station.readings.get(station.readings.size() -1).temperature);
            station.wind = getWind(station.readings.get(station.readings.size() -1).windSpeed);
            station.pressure = station.readings.get(station.readings.size() -1).pressure + "hpa";
        }

        System.out.println(stations.get(1).readings.get(1).code);
        render("dashboard.html", stations);
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

    public static String getTemp(float tempCelcius) {
        double tempFahrenheit = tempCelcius * (9.0/5.0) + 32;
        return tempCelcius + "C\n" + tempFahrenheit + "F";
    }

    public static String getWind(float windSpeed) {
        List<float[]> conversions = new ArrayList<float[]>();
        conversions.add(new float[] {0, 1, 0});
        conversions.add(new float[] {1, 5, 1});
        conversions.add(new float[] {6, 11, 2});
        conversions.add(new float[] {12, 19, 3});
        conversions.add(new float[] {20, 28, 4});
        conversions.add(new float[] {29, 38, 5});
        conversions.add(new float[] {39, 49, 6});
        conversions.add(new float[] {50, 61, 7});
        conversions.add(new float[] {63, 74, 8});
        conversions.add(new float[] {75, 88, 9});
        conversions.add(new float[] {89, 102, 10});
        conversions.add(new float[] {103, 117, 12});

        for (float[] c: conversions) {
            if (windSpeed > c[0] && windSpeed <= c[1]) {return c[2] + "bft";}
        }
        return "n/a";
    }

}
