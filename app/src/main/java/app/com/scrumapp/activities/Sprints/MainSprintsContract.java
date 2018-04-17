package app.com.scrumapp.activities.Sprints;

import java.util.List;

import app.com.scrumapp.BasePresenter;
import app.com.scrumapp.BaseView;
import app.com.scrumapp.models.Proyecto;
import app.com.scrumapp.models.Sprint;

/**
 * Created by Fernando on 15/04/2018.
 */

public interface MainSprintsContract {

    interface View extends BaseView<Presenter> {
        void setRecycler(List<Sprint> sprints);
    }

    interface Presenter extends BasePresenter {
        void getSprints(int id_proyecto);
    }
}
