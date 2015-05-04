package models;

import java.util.*;
import play.db.ebean.*;
import play.db.ebean.Model.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;

@Entity
public class User extends Model {

    @Id
	public Long id;
	
	@Required
    public String name;
	
	// Dates and times will be in standard UNIX timestamps
	// to make time math easier
	public Long lastLoggedIn;
	
	@Required
	public String emailAddress;
	public Boolean allowsNotifications;
	
	// timeZone will be an Int of hours away from UTC
	// Example: Central Time will be +6 hours 
	public Integer timeZone;
	
	@Required
	public String passwordHash;
	public String authToken;
	public String hashSalt;
	
	@OneToMany
	private List<Event> eventList;
	
	public static Finder<Long, User> find = new Finder(
	    Long.class, User.class
	);
	
	public static List<User> all() {
		return find.all();
	}

	public static void create(User user) {
		user.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
}