package controllers;

import models.Member;
import play.mvc.Controller;

import java.util.List;
import java.util.ArrayList;

/**
 * Controller class for handling requests relating to "Member".
 */
public class MemberCtrl extends Controller {

    /**
     * Returns current Member object
     */
    public static Member getCurrentMember() {
        String memberId = session.get("logged_in_Memberid");
        return Member.findById(Long.parseLong(memberId));
    }

    public static void member() {
        Member member = getCurrentMember();
        render("member.html", member);
    }

    /**
     * Handles POST request to edit Member fields and redirects to member route (email CANNOT be edited).
     *
     * @param firstname - member's first name (String)
     * @param lastname  - member's last name (String)
     * @param password  - member's password (String)
     */
    public static void editMemberDetails(String firstname, String lastname, String password) {
        String memberId = session.get("logged_in_Memberid");
        Member member = Member.findById(Long.parseLong(memberId));
        if (firstname != null) {
            member.firstName = firstname;
        }
        if (lastname != null) {
            member.lastName = lastname;
        }
        if (password != null) {
            member.password = password;
        }
        member.save();
        redirect("/member");
    }

    /**
     * Renders the "login.html" if user is not already logged in.
     *
     * @param firstAttempt Indicates whether it is the first login attempt or not.
     *                     If true, the login page is rendered without displaying any error message.
     *                     If false, the login page is rendered with an "incorrect password" message.
     */
    public static void login(Boolean firstAttempt) {
        firstAttempt = firstAttempt == null ? true : firstAttempt;
        if (!session.contains("logged_in_Memberid")) {
            render("login.html", firstAttempt);
        }
        Member member = getCurrentMember();
        if (member == null) {   // We use Boolean object in param (as opposed to primitive), so we can check null
            render("login.html", firstAttempt);
        }
        redirect("/");
    }


    /**
     * Handles logout
     */
    public static void logout() {
        session.clear();
        redirect("/");
    }

    /**
     * Handles the login action and redirects to dashboard route if successful
     *
     * @param email    - member's email (String)
     * @param password - member's password (String)
     */
    public static void authenticate(String email, String password) {
        Member member = Member.findByEmail(email);
        if (member != null && member.checkPassword(password)) {
            session.put("logged_in_Memberid", member.id);   // Setting session cookie
            redirect("/dashboard");
        }
        login(false);   // Unsuccessful login, we try again, firstAttempt arg is set to false
    }

    /**
     * Renders the "signup.html" if user is not already logged in.
     *
     * @param firstAttempt Indicates whether it is the first login attempt or not.
     *                     If true, the signup page is rendered without displaying any error message.
     *                     If false, the login page is rendered with an "email already exists" message.
     */
    public static void signup(Boolean firstAttempt) {
        firstAttempt = firstAttempt == null ? true : firstAttempt;
        render("signup.html", firstAttempt);
    }

    /**
     * Handles the signup action and redirects to dashboard route if successful
     * To note a lot of the validation is done client side (should really have been done here)
     *
     * @param firstname - member's first name (String)
     * @param lastname  - member's last name (String)
     * @param email     - member's email (String)
     * @param password  - member's password (String)
     */
    public static void register(String firstname, String lastname, String email, String password) {
        List<Member> members = Member.findAll();
        ArrayList<String> emails = new ArrayList<>();
        for (Member member : members) {
            emails.add(member.email);
        }
        if (emails.contains(email)) {
            signup(false);  // Unsuccessful as email already exists
        } else {
            Member member = new Member(firstname, lastname, email, password);
            member.save();
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        }
    }
}
