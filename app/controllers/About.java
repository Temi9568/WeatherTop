package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class About extends Controller
{
  public static void index() {
    boolean loggedIn = session.contains("logged_in_Memberid");
    render ("about.html", loggedIn);
  }
}

