package models;

import java.util.*;
import play.db.ebean.*;
import play.db.ebean.Model.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class User extends Model {

    @Id
	public Long id;
	
	@Required
    public String name;
	
	// Dates and times will be in standard UNIX timestamps
	// to make time math easier
	public Long lastLoggedIn;
	
	@Column(unique = true)
	@MaxLength(256)
    @Required
    @Email
	public String emailAddress;
	
	public Boolean allowsNotifications;
	
	// timeZone will be an Int of hours away from UTC
	// Example: Central Time will be +6 hours 
	public Integer timeZone;
	
	@Required
	public String passwordHash;
	public String authToken;
	public Long authTokenExpirationDate;
	
	@OneToMany
	private List<Event> eventList;
	
	public static Finder<Long, User> find = new Finder(
	    Long.class, User.class
	);
	
	public static List<User> all() {
		return find.all();
	}

	public static User create(String name, String emailAddress, String password) {
		User user = new User();
		
		user.name = name;
		user.emailAddress = emailAddress;
		
		// Use the BCrypt Algorithm for generating password hashes
		// User.passwordHash is sent to create() as plaintext and turned into a hash
		user.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
		
		user.save();
		return user;
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	// Returns authToken String if email/password is correct
	public String authenticate() {
		User user = User.find.where().eq("emailAddress", this.emailAddress).findUnique();
		if (user != null && BCrypt.checkpw(this.passwordHash, user.passwordHash)) {
			user.authToken = UUID.randomUUID().toString();
			
			// Date.getTime returns unix timestamp in milliseconds, we need to convert that to seconds
			// Then add 1 (30-Day) month
			Long millisecondsInASecond = new Long(1000);
			Long secondsInAMonth = new Long(2592000);
			
			Date now = new Date();
			
			user.authTokenExpirationDate = (now.getTime() / millisecondsInASecond) + secondsInAMonth;
			
			user.save();
			return user.authToken;
		} else {
			return null;
		}
	}
}