package app.com.scrumapp.activities.login;

import android.content.Context;

public interface LoginILogic {

    void validateUser(String email, String password, CallBackResponse response);
    void logignOff( CallBackResponse response, Context context);
}
