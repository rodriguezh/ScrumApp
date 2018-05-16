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

public class MainSprintsPresenter implements MainSprintsContract.Presenter, CallBackResponse{

    private static final String TAG = "MainProjectsPresenter";
    @NonNull
    private final MainSprintsContract.View view;

    MainSprintsLogic mainSprintLogic = MainSprintsLogic.getIntance();

    private int id_proyecto;

    public MainSprintsPresenter(@NonNull MainSprintsContract.View view, int id_proyecto) {
        this.view = view;

        this.id_proyecto=id_proyecto;
    }

    @Override
    public void start() {
        getSprints(id_proyecto);
    }

    @Override
    public void getSprints(int id_proyecto) {
        mainSprintLogic.getSprints(id_proyecto,this);
    }

    @Override
    public void onSuccess(Object object, String nameMethod) {
        view.setRecycler((List<Sprint>) object);
    }

    @Override
    public void onnError(Object object) {
        view.showInfoMessage(object.toString());
    }
}
