package app.com.scrumapp.activities.proyectos;

import android.support.annotation.NonNull;
import android.util.Log;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.model.SprintProjectResponse;
import app.com.scrumapp.data.remote.retrofit.APIServiceProjects;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando on 15/04/2018.
 */

public class MainProjectsPresenter implements MainProjectsContract.Presenter{

    private static final String TAG = "MainProjectsPresenter";
    @NonNull
    private final MainProjectsContract.View view;

    private APIServiceProjects apiService;

    public MainProjectsPresenter(@NonNull MainProjectsContract.View view) {
        this.view = view;
        this.apiService  = ApiUtils.getAPIServiceProject(Constants.BASE_URLSPRINTB);
    }

    @Override
    public void start() {
        getProjects();
    }

    @Override
    public void getProjects() {
        apiService.getProyectos().enqueue(new Callback<SprintProjectResponse>() {
            @Override
            public void onResponse(Call<SprintProjectResponse> call, Response<SprintProjectResponse> response) {
                if(response.isSuccessful()) {
                    view.setRecycler(response.body().getMessage());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }else{
                    Log.e(TAG, "Unable to submit post to API." + response.message());
                }
            }

            @Override
            public void onFailure(Call<SprintProjectResponse> call, Throwable t) {
                Log.e(TAG, "Unable to get to API." + t.getMessage());
                view.showInfoMessage("No es posible consular la informacion");
            }
        });
    }
}
