package app.com.scrumapp.fragments.huinicial;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.model.HistoriadeUsuarioInicial;
import app.com.scrumapp.data.model.SprintBacklogResponse;
import app.com.scrumapp.data.remote.retrofit.APIServiceSprintBacklog;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

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

        /*apiService.getHistoriasUsuarioIni(id_sprint).enqueue(new Callback<SprintBacklogResponse>() {
            @Override
            public void onResponse(Call<SprintBacklogResponse> call, Response<SprintBacklogResponse> response) {
                if(response.isSuccessful()) {
                    mView.loadView(response.body().getHistoriadeUsuarioInicials());
                    Log.i(TAG, "post submitted to API." + response.body().getHistoriadeUsuarioInicials().toString());
                }else{
                    Log.e(TAG, "Unable to submit post to API." + response.body()+"\n"+response.message()+"\n"+response.raw());
                }
            }

            @Override
            public void onFailure(Call<SprintBacklogResponse> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
            }
        });*/
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
