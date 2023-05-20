package controllers;

import play.mvc.*;


/**
 * Controller class for handling the "About" page.
 */
public class About extends Controller {
    /**
     * This renders the "about.html" markup template and a loggedIn (bool) variable is passed to the template.
     */
    public static void index() {
        boolean loggedIn = session.contains("logged_in_Memberid");
        render("about.html", loggedIn);
    }
}

