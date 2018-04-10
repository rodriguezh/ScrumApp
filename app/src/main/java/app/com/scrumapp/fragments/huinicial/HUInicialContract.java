package app.com.scrumapp.fragments.huinicial;

import java.util.List;

import app.com.scrumapp.BasePresenter;
import app.com.scrumapp.BaseView;
import app.com.scrumapp.data.model.HistoriadeUsuarioInicial;

public interface HUInicialContract {

    interface View extends BaseView<Presenter> {
        void loadView(List<HistoriadeUsuarioInicial> list);

    }

    interface Presenter extends BasePresenter {

        void getUsesrHistory(int id_pb, int id_sprint);


    }
}
