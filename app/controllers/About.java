package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class About extends Controller
{
  public static void index() {
    if (!session.contains("logged_in_Memberid")) { redirect("/login");}
    Logger.info("Rendering about");
    render ("about.html");
  }
}

