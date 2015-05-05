package helpers;

import controllers.Users;
import controllers.routes;
import models.User;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class SecurityHelper extends Security.Authenticator {  
	public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
	public static final String AUTH_TOKEN = "authToken";
		
	// We're using getUsername to find by emailAddress, a bit of a misnomer
    @Override
    public String getUsername(Http.Context ctx) {
        String token = getTokenFromHeader(ctx);
        if (token != null) {
            User user = User.find.where().eq("authToken", token).findUnique();
            if (user != null) {
                return user.emailAddress;
            }
        }
        return null;
    }
 
    @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }
 
    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }
}