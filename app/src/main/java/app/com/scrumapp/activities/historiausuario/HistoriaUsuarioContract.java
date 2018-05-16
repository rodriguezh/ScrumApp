package app.com.scrumapp.activities.historiausuario;

import java.util.ArrayList;
import java.util.List;

import app.com.scrumapp.BasePresenter;
import app.com.scrumapp.BaseView;
import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;

public interface HistoriaUsuarioContract {

    interface View extends BaseView<Presenter> {

        void loadView(HistoriadeUsuario hu);

        void showInfoMessage(String respuesta);

        void setSpinnerUser(ArrayList<Usuario> usuarios);

        void setTimeChronometer(int min, int seg);
    }

    interface Presenter extends BasePresenter {

        void saveUserHistory (HistoriadeUsuario hu);

        void createUserHistory (HistoriadeUsuario hu);

        void getUserHistory(String idHu);

        void getUsers();

        int timetoSeg(String time);

    }
}
