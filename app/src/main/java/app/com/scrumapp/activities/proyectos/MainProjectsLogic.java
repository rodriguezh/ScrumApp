package app.com.scrumapp.activities.proyectos;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.model.SprintProjectResponse;
import app.com.scrumapp.data.remote.retrofit.APIServiceLogin;
import app.com.scrumapp.data.remote.retrofit.APIServiceProjects;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando on 16/04/2018.
 */

public class MainProjectsLogic implements MainProjectsILogic {

    private static final String TAG = "MainProjectsLogic";
    private static MainProjectsLogic instance = null;
    private APIServiceProjects apiService;
    private SharedPreferences prefs;
    private APIServiceLogin apiServicelogin;
    public MainProjectsLogic() {
     this.apiService  = ApiUtils.getAPIServiceProject(Constants.BASE_URLSPRINTB);

    }

    public static MainProjectsLogic getIntance(){
        if(instance == null){
            instance = new MainProjectsLogic();
        }

        return instance;
    }

    @Override
    public void logignOff(final CallBackResponse callBackResponse, final Context context) {

    }

    private void deletePreferences(Context context){
        prefs = context.getSharedPreferences("Preferences", context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }

    @Override
    public void getProjects(final CallBackResponse responses) {
        apiService.getProyectos().enqueue(new Callback<SprintProjectResponse>() {
            @Override
            public void onResponse(Call<SprintProjectResponse> call, Response<SprintProjectResponse> response) {
                if(response.isSuccessful()) {
                   // view.setRecycler(response.body().getMessage());
                    responses.onSuccess(response.body().getMessage(),"getProjects",null);
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }else{
                    Log.e(TAG, "Unable to submit post to API." + response.message());
                    responses.onnError(response.message());
                }
            }

            @Override
            public void onFailure(Call<SprintProjectResponse> call, Throwable t) {
                Log.e(TAG, "Unable to get to API." + t.getMessage());
               // view.showInfoMessage("No es posible consular la informacion");
                responses.onnError(t.getMessage());
            }
        });
    }
}
