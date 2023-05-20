package controllers;

import play.mvc.*;


import models.*;

/**
 * Controller class for handling the "Home" page.
 */
public class Home extends Controller {
    /**
     * Renders "index.html" template and a Member object (if exists) is passed to the template, if not false is passed.
     */
    public static void index() {
        try {
            Member member = MemberCtrl.getCurrentMember();
            render("/index.html", member);
        } catch (NumberFormatException e) { // getCurrentMember() func converts String to Long, catches exception here.
            boolean member = false;
            render("/index.html", member);
        }
    }


}