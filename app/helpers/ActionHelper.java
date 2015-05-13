package helpers;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import models.User;

public class ActionHelper extends Action.Simple {
    public F.Promise<Result> call(Http.Context ctx) throws Throwable {
        String token = getTokenFromHeader(ctx);
        if (token != null) {
            User user = User.find.where().eq("token", token).findUnique();
            if (user != null) {
                ctx.request().setUsername(user.emailAddress);
                return delegate.call(ctx);
            }
        }
        Result unauthorized = redirect("/");
        return F.Promise.pure(unauthorized);
    }

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }
}