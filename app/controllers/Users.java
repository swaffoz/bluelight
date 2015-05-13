package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import helpers.*;
import play.libs.Json ; 

import views.html.*;

public class Users extends Controller {

	static Form<User> userForm = Form.form(User.class);

    public static Result index() {
        return ok(index.render(userForm));
    }

    public static Result users() {
        return ok(views.html.users.render(User.all(), userForm));
    }
	
	public static Result newUser() {
		Form<User> filledForm = userForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			//return badRequest(views.html.users.render(User.all(), filledForm));
		} 
		
		User user = User.create(filledForm.get().name, filledForm.get().emailAddress, filledForm.get().passwordHash);
		
		//String authToken = filledForm.get().authenticate();
		
		//return ok(authToken);
		return redirect(routes.Users.testEvents());
	}
	
	public static Result authenticateUser() {
		Form<User> filledForm = userForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			//return badRequest(views.html.users.render(User.all(), filledForm));
		} 
		
		//String authToken = filledForm.get().authenticate();
		//Logger.debug("authtoken = " + authToken);
		return redirect(routes.Users.testEvents());
	}

	public static Result deleteUser(Long id) {
		User.delete(id);
		return redirect(routes.Users.users());
	}
	
	public static Result getEvents(Long id) {
		User user = User.find.byId(id) ; 
		if ( user != null ) {
			return ok(Json.toJson(user.eventList)); 
		}
		Logger.info( "Returning bad request" ); 
		return badRequest(); 
	}
	
	public static Result testEvents() { 
		return ok(views.html.eventsTest.render());
	}
	
	@With(ActionHelper.class)
	public static Result testSecrets() {
		return ok("You found me!");
	}
}
