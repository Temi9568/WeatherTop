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
                station = StationCtrl.setStationExtraFields(station, r);    // really shouldn't be altering LCV here
            }
        }

        member.stations.sort((s1, s2) -> s1.name.compareToIgnoreCase(s2.name)); // Sots station in alphabetical order
        render("dashboard.html", member, isDashboard);   // render dashboard.html
    }


}
