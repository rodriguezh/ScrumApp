package app.com.scrumapp.activities.historiausuario;

import app.com.scrumapp.BasePresenter;
import app.com.scrumapp.BaseView;
import app.com.scrumapp.models.HistoriadeUsuario;

public interface HistoriaUsuarioContract {

    interface View extends BaseView<Presenter> {

        void loadView(HistoriadeUsuario hu);

        void showInfoMessage(String respuesta);
    }

    interface Presenter extends BasePresenter {

        void saveUserHistory (int Sprint, String criteriosAceptacion, String descripcion, int idHu,String nombreHu, int peso, int prioridad);

        void getUserHistory(String idHu);

    }
}
