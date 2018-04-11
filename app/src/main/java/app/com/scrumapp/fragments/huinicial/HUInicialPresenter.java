package app.com.scrumapp.fragments.huinicial;
import android.support.annotation.NonNull;
import java.util.List;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.model.HistoriadeUsuarioInicial;

import app.com.scrumapp.data.remote.retrofit.APIServiceSprintBacklog;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;


public class HUInicialPresenter implements HUInicialContract.Presenter, CallBackResponse {

    @NonNull
    private final HUInicialContract.View mView;
    @NonNull
    private int id_pb, id_sprint;

    private APIServiceSprintBacklog apiService;

    private List<HistoriadeUsuarioInicial> historiadeUsuarioInicials;


    private LogicHUInicial logicHUInicial = LogicHUInicial.getIntance();

    public HUInicialPresenter(@NonNull HUInicialContract.View mView, @NonNull int id_pb, @NonNull int id_sprint) {
        this.mView = mView;
        this.id_pb = id_pb;
        this.id_sprint = id_sprint;
        apiService = ApiUtils.getAPIService(Constants.BASE_URLSPRINTB);
    }


    @Override
    public void getUsesrHistory(int id_pb, int id_sprint) {

        logicHUInicial.list(1,1,this);
    }

    @Override
    public void start() {
        getUsesrHistory(id_pb,id_sprint);
    }

    @Override
    public void onSuccess(Object object) {
        mView.loadView((List<HistoriadeUsuarioInicial>) object);
    }

    @Override
    public void onnError(Object object) {

    }
}
