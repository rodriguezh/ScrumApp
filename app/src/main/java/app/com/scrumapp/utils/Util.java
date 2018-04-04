package app.com.scrumapp.utils;

import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

    public static String getUserMailPrefs(SharedPreferences preferences){
        return preferences.getString("email","");
    }

    public static String getUserPassPrefs(SharedPreferences preferences){
        return  preferences.getString("pass","");
    }

    public static String formatFechaActual(){
        String codg=new SimpleDateFormat( "yyyy-MM-dd", java.util.Locale.getDefault()).format(Calendar.getInstance().getTime());
        return codg;
    }

}
