package app.com.scrumapp.activities.proyectos;

import android.content.Context;

import java.util.List;

import app.com.scrumapp.BasePresenter;
import app.com.scrumapp.BaseView;
import app.com.scrumapp.models.Proyecto;

/**
 * Created by Fernando on 15/04/2018.
 */

public interface MainProjectsContract {

    interface View extends BaseView<Presenter> {
        void setRecycler(List<Proyecto> projects);
    }

    interface Presenter extends BasePresenter {
        void getProjects();
        void logOut(Context context);
    }
}
