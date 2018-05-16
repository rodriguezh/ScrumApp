package app.com.scrumapp.fragments.huinicial;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

import app.com.scrumapp.Constants;

import app.com.scrumapp.data.model.HUInicialResponse;
import app.com.scrumapp.data.model.SprintBacklogResponse;
import app.com.scrumapp.data.remote.retrofit.APIServiceSprintBacklog;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogicHUInicial implements  ILogicHUInicial {


    private static LogicHUInicial instance = null;
    private APIServiceSprintBacklog apiService;

    protected LogicHUInicial() {
        apiService = ApiUtils.getAPIService(Constants.BASE_URLSPRINTB);
    }

    public static LogicHUInicial getIntance(){
        if(instance == null){
            instance = new LogicHUInicial();
        }
        return instance;
    }

    @Override
    public void list(int id_pb, int id_sprint, final CallBackResponse callBackResponse) {

        apiService.getHistorias(id_pb,id_sprint).enqueue(new Callback<SprintBacklogResponse>() {
            @Override
            public void onResponse(Call<SprintBacklogResponse> call, Response<SprintBacklogResponse> response) {
                if(response.isSuccessful()) {
                    callBackResponse.onSuccess(response.body().getMessage());
                    Log.i(TAG, "post submitted to API." + response.body().getMessage().toString());
                }else{
                    Log.e(TAG, "Unable to submit post to API." + response.body()+"\n"+response.message()+"\n"+response.raw());
                }
            }

            @Override
            public void onFailure(Call<SprintBacklogResponse> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API." + t.toString());
                callBackResponse.onnError(t.getMessage());
                callBackResponse.onSuccess(new ArrayList<HUInicialResponse>());
            }
        });

    }


}
