package controllers;

import models.Member;
import play.mvc.Controller;

import java.util.List;
import java.util.ArrayList;

/**
 * Controller class for handling the "Member" page.
 */
public class Account extends Controller {

    public static Member getCurrentMember() {
        String memberId = session.get("logged_in_Memberid");
        return Member.findById(Long.parseLong(memberId));
    }

    public static void editMemberDetails(String firstname, String lastname, String email, String password) {
        String memberId = session.get("logged_in_Memberid");
        Member member = Member.findById(Long.parseLong(memberId));
        if (firstname != null ) { member.firstName = firstname;}
        if (lastname != null ) {  member.lastName = lastname;}
        if (email != null ) {  member.email = email;}
        if (password != null ) {  member.password = password;}
//        member.save();
        redirect("/member");
    }



    public static void memberPage() {
        Member member = getCurrentMember();
        render("member.html", member);
    }


    public static void login(Boolean firstAttempt) {
        firstAttempt = firstAttempt == null ? true : firstAttempt;
        if (!session.contains("logged_in_Memberid")) {
            render("login.html", firstAttempt);
        }
        Member member = getCurrentMember();
        if (member == null) {
            render("login.html", firstAttempt);
        }
        redirect("/");
    }


    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static void authenticate(String email, String password) {
        Member member = Member.findByEmail(email);
        if (member != null && member.checkPassword(password)) {
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        }
        login(false);
    }

    public static void signup(Boolean firstAttempt) {
        firstAttempt = firstAttempt == null ? true : firstAttempt;
        render("signup.html", firstAttempt);
    }

    public static void register(String firstname, String lastname, String email, String password) {
        List<Member> members = Member.findAll();
        ArrayList<String> emails = new ArrayList<>();
        for (Member member : members) {
            emails.add(member.email);
        }
        if (emails.contains(email)) {
            signup(false);
        } else {
            Member member = new Member(firstname, lastname, email, password);
            member.save();
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        }
    }
}
