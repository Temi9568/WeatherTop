package controllers;
import models.Member;
import play.mvc.Controller;

public class Account extends Controller {

    public static Member getCurrentMember() {
        String memberId = session.get("logged_in_Memberid");
        return  Member.findById(Long.parseLong(memberId));
    }

    public static void login() {
        render("login.html");
    }


    public static void logout() {
       session.clear();
       redirect("/login");
    }

    public static void authenticate(String email, String password) {
        Member member = Member.findByEmail(email);
        if (member != null && member.checkPassword(password)) {
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        }
        redirect("/login");
    }

    public static void signup() {
        render("signup.html");
    }
    public static void register(String firstname, String lastname, String email, String password) {
        Member member = new Member(firstname, lastname, email, password);
        member.save();
        session.put("logged_in_Memberid", member.id);
        redirect("/dashboard");
    }
}
