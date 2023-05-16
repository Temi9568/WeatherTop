package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
//        if (!session.contains("logged_in_Memberid")) { redirect("/login");}
        try {
            Member member = Account.getCurrentMember();
            render("/index.html", member);
        } catch (NumberFormatException e) {
            boolean member = false;
            render("/index.html", member);
        }
    }



}