package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;

import views.html.*;

public class Events extends Controller {

	static Form<Event> eventForm = Form.form(Event.class);
	
    public static Result events() 
	{
		 return ok(views.html.events.render(Event.all(), eventForm));
    }
	
	public static Result newEvent()
	{
		Form<Event> filledForm = eventForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.events.render(Event.all(), filledForm));
		} else {
			Event.create(filledForm.get());
			return redirect(routes.Events.events());  
		}
	}

	public static Result deleteEvent(Long id) 
	{
		Event.delete(id);
		return redirect(routes.Events.events());
	}
	
	public static Result testEvents() { 
		return ok(views.html.eventsTest.render());
	}
}
