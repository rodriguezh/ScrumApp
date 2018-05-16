package app.com.scrumapp.activities.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.internal.LinkedTreeMap;

import java.lang.reflect.Array;
import java.util.ArrayList;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.model.HUInicialResponse;
import app.com.scrumapp.data.model.SprintBacklogResponse;
import app.com.scrumapp.data.model.Userlogin;
import app.com.scrumapp.data.remote.retrofit.APIServiceLogin;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import app.com.scrumapp.models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class LoginLogic implements LoginILogic {

    private static LoginLogic instance = null;
    private APIServiceLogin apiService;
    private SharedPreferences prefs;
    protected LoginLogic(){
        apiService = ApiUtils.getAPIServiceLogin(Constants.BASEURLLOGIN);
    }

    public static LoginLogic getInstance(){
        if(instance == null){
            instance = new LoginLogic();
        }

        return instance;
    }

    @Override
    public void validateUser(String email, String password, final CallBackResponse callBackResponse) {

        apiService.loginUser(email,password).enqueue(new Callback<ArrayList<Userlogin>>() {
            @Override
            public void onResponse(Call<ArrayList<Userlogin>> call, Response<ArrayList<Userlogin>> response) {
               // Log.e(TAG,response.body().get(0).toString());
                if(response.isSuccessful() && response.body() instanceof ArrayList) {
                         callBackResponse.onSuccess(response.body().get(0),"validateUser",null);
                }else{
                    callBackResponse.onnError(response.body().toString());
                    Log.e(TAG, "Unable to submit post to API." + response.body()+"\n"+response.message()+"\n"+response.raw());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Userlogin>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
                callBackResponse.onnError(t.getMessage());
                //callBackResponse.onSuccess(new ArrayList<HUInicialResponse>(),"validateUser");
            }
        });

    }

    @Override
    public void logignOff(final CallBackResponse callBackResponse, final Context context) {
        prefs = context.getSharedPreferences("Preferences", context.MODE_PRIVATE);
        apiService.loginOff(prefs.getString("usuario", ""),"off").enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                LinkedTreeMap<String,String> linkedTreeMap = (LinkedTreeMap<String, String>) response.body();
                if (linkedTreeMap.containsKey("sesion")){
                    callBackResponse.onSuccess(response.body().toString(),"loginOff",context);
                    deletePreferences(context);
                }else{
                    callBackResponse.onnError(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callBackResponse.onnError(t.getMessage());
            }
        });
    }

    private void deletePreferences(Context context){
        prefs = context.getSharedPreferences("Preferences", context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
