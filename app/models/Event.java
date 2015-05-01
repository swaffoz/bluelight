package models;

import java.util.*;
import play.db.ebean.*;
import play.db.ebean.Model.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;

@Entity
public class Event extends Model {

	@Id
	public Long id;
	
	@Required
 	public String name;
	
	// Dates and times will be in standard UNIX timestamps
	// to make time math easier
	public Long createdAt;
	public Long startTime;
	public Long endTime;
	public Boolean isRepeating;
	
	// repeatsEvery is a timestamp so we can put 
	// whatever amount of time we want for repeating.
	public Long repeatsEvery;
	public Boolean notifiesUser;
	
	// We will wait to implement Tags.
	// public String tags;
	
	public String color;
	
	public static Finder<Long, Event> find = new Finder(
	    Long.class, Event.class
	);
	
	public static List<Event> all() {
		return find.all();
	}

	public static void create(Event event) {
		event.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
}