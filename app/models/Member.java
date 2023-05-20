package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Member object for each member of WeatherTop
 */
@Entity
public class Member extends Model {

    public String firstName, lastName, email, password;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Station> stations = new ArrayList<Station>();

    /**
     * Creates a new Member object (Constructor)
     *
     * @param firstName member's first name
     * @param lastName  member's last name
     * @param email     member's email address.
     * @param password  member's password
     */
    public Member(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns a Member object by using their email to find them.
     *
     * @param email email address of Member we want to find (String)
     * @return Member object of member with email address we specified.
     */
    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    /**
     * Checks if the provided password matches the member's password.
     *
     * @param password The password we want to check (boolean)
     * @return True if password is correct else false.
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}

