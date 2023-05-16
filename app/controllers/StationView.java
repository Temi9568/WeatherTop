package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;


public class StationView extends Controller {

    public static void index(Long id) {
        if (!session.contains("logged_in_Memberid")) { redirect("/login");}
        Station station = Station.findById(id);
        render("station-dashboard.html", station);
    }

}
