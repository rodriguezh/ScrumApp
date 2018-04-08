package app.com.scrumapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Fabián E. Capote on 1/09/2016.
 */
public class TestConexion {


    public static  boolean isConnected(Context contexto) {
        Context v_context=contexto;

        ConnectivityManager cm =
                (ConnectivityManager) v_context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
         //Log.e("activeNetwork",activeNetwork+"");

        if(activeNetwork!=null){
            Log.e("activeNetwork no null",activeNetwork+"");
            return activeNetwork.isConnectedOrConnecting();
        }else{
            Log.e("activeNetwork es null",activeNetwork+"");
            return false;
        }

       // return   (activeNetwork!=null)?activeNetwork.isConnectedOrConnecting():false;


    }

}
