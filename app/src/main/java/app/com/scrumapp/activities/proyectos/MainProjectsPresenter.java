package app.com.scrumapp.activities.proyectos;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import app.com.scrumapp.Constants;
import app.com.scrumapp.activities.login.LoginActivity;
import app.com.scrumapp.data.model.SprintProjectResponse;
import app.com.scrumapp.data.model.Userlogin;
import app.com.scrumapp.data.remote.retrofit.APIServiceProjects;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import app.com.scrumapp.models.Proyecto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando on 15/04/2018.
 */

public class MainProjectsPresenter implements MainProjectsContract.Presenter, CallBackResponse{

    private static final String TAG = "MainProjectsPresenter";
    @NonNull
    private final MainProjectsContract.View view;

    MainProjectsLogic mainProjectsLogic = MainProjectsLogic.getIntance();

    public MainProjectsPresenter(@NonNull MainProjectsContract.View view) {
        this.view = view;

    }

    @Override
    public void start() {
        getProjects();
    }

    @Override
    public void getProjects() {
        mainProjectsLogic.getProjects(this);
    }

    @Override
    public void logOut(Context context) {
        mainProjectsLogic.logignOff(this,context);
    }

    @Override
    public void onSuccess(Object object, String nameMethod, Context context) {
        switch (nameMethod){
            case "getProjects":
                view.setRecycler((List<Proyecto>) object);
                break;
            case "loginOff":
                // mProfileView.logOut();
                // mProfileView.showInfoMessage(object.toString());
                Toast.makeText(context,object.toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,LoginActivity.class);
                //borrar  el historial apenas salga de la sesión por seguridad
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                break;
        }
    }

    @Override
    public void onnError(Object object) {
        view.showInfoMessage(object.toString());
    }
}
