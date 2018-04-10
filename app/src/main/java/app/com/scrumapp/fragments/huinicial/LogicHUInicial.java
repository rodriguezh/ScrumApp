package app.com.scrumapp.fragments.huinicial;

import android.app.Activity;
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

public class LogicHUInicial implements  ILogicHUInicial {

    private APIServiceSprintBacklog apiService;

    public LogicHUInicial() {
      //  this.apiService = apiService;
        apiService = ApiUtils.getAPIService(Constants.BASE_URLSPRINTB);
    }
    String s;
    @Override
    public void list(int id_pb, int id_sprint, final CallBackResponse callBackResponse) {

        apiService.getHistoriasUsuarioIni(id_sprint).enqueue(new Callback<SprintBacklogResponse>() {
            @Override
            public void onResponse(Call<SprintBacklogResponse> call, Response<SprintBacklogResponse> response) {
                if(response.isSuccessful()) {

                    callBackResponse.onSuccess(response.body().getHistoriadeUsuarioInicials());

                    Log.i(TAG, "post submitted to API." + response.body().getHistoriadeUsuarioInicials().toString());
                }else{
                    Log.e(TAG, "Unable to submit post to API." + response.body()+"\n"+response.message()+"\n"+response.raw());

                }
            }

            @Override
            public void onFailure(Call<SprintBacklogResponse> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
            }
        });

    }


}
