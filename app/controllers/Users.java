package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
	
import views.html.*;

public class Users extends Controller {

	static Form<User> userForm = Form.form(User.class);

    public static Result users() {
        return ok(views.html.users.render(User.all(), userForm));
    }
	
	public static Result newUser() {
		Form<User> filledForm = userForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(views.html.users.render(User.all(), filledForm));
		} else {
			User.create(filledForm.get());
			return redirect(routes.Users.users());  
		}
	}

	public static Result deleteUser(Long id) {
		User.delete(id);
		return redirect(routes.Users.users());
	}
}
