package controllers;

import utils.*;

import models.*;
import play.mvc.Controller;


/**
 * Controller class for handling the "Dashboard" page.
 */
public class Dashboard extends Controller {
    /**
     * Renders "dashboard.html" template and a Member object and (bool indicating its dashboard) is passed to template.
     */
    public static void index() {
        if (!session.contains("logged_in_Memberid")) {
            redirect("/login");
        } // redirect to login page if not logged.
        Member member = MemberCtrl.getCurrentMember(); // Gets member object
        boolean isDashboard = true;

        // Following code sets the stations additional field which is used for the station panes.
        for (Station station : member.stations) {
            if (station.readings.size() >= 1) { // If no readings then unable to set additional station fields.
                Reading r = station.readings.get(station.readings.size() - 1);  // get most recent reading

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
            }
        }

        member.stations.sort((s1, s2) -> s1.name.compareToIgnoreCase(s2.name)); // Sots station in alphabetical order
        render("dashboard.html", member, isDashboard);   // render dashboard.html
    }


}
