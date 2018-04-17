package app.com.scrumapp.activities.Sprints;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.model.ApiResponse;
import app.com.scrumapp.data.model.SprintProjectResponse;
import app.com.scrumapp.data.remote.retrofit.APIServiceProjects;
import app.com.scrumapp.data.remote.retrofit.APIServiceSprintBacklog;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import app.com.scrumapp.models.Sprint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando on 15/04/2018.
 */

public class MainSprintsPresenter implements MainSprintsContract.Presenter{

    private static final String TAG = "MainProjectsPresenter";
    @NonNull
    private final MainSprintsContract.View view;

    private APIServiceSprintBacklog apiService;

    private int id_proyecto;

    public MainSprintsPresenter(@NonNull MainSprintsContract.View view, int id_proyecto) {
        this.view = view;
        this.apiService  = ApiUtils.getAPIService(Constants.BASE_URLSPRINTB);
        this.id_proyecto=id_proyecto;
    }

    @Override
    public void start() {
        getSprints(id_proyecto);
    }

    @Override
    public void getSprints(int id_proyecto) {
        apiService.getSprints(id_proyecto).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful()) {
                    try {
                       view.setRecycler(response.body().getMessage());
                    }catch (Exception e){
                        view.showInfoMessage("No es posible consular la informacion");
                    }
                    Log.i(TAG, "post submitted to API  222  " + response.body().getMessage().get(0).getNombre_pb());
                }else{
                    Log.e(TAG, "Unable to submit post to API." + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "Unable to get to API." + t.getMessage());
                view.showInfoMessage("No es posible consular la informacion");
            }
        });
    }
}
