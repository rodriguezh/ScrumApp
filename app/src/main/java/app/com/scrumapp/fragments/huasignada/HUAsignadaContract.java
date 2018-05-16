package app.com.scrumapp.fragments.huasignada;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import app.com.scrumapp.BasePresenter;
import app.com.scrumapp.BaseView;
import app.com.scrumapp.models.HistoriadeUsuario;

/**
 * Created by Fernando on 16/04/2018.
 */

public interface HUAsignadaContract {

    interface View extends BaseView<Presenter> {
        void loadView(FirestoreRecyclerOptions<HistoriadeUsuario> options);

    }

    interface Presenter extends BasePresenter {
        void getUsesrHistory(int id_pb, int id_sprint);
    }

}
