package app.com.scrumapp.activities.Sprints;

import android.util.Log;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.model.ApiResponse;
import app.com.scrumapp.data.remote.retrofit.APIServiceSprintBacklog;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fernando on 16/04/2018.
 */

public class MainSprintsLogic implements MainSprintsILogic {

    private static final String TAG = "MainSprintsLogic" ;
    private static MainSprintsLogic instance = null;
    private APIServiceSprintBacklog apiService;


    public MainSprintsLogic() {
        this.apiService  = ApiUtils.getAPIService(Constants.BASE_URLSPRINTB);
    }

    public static MainSprintsLogic getIntance(){
        if(instance == null){
            instance = new MainSprintsLogic();
        }

        return instance;
    }

    @Override
    public void getSprints(int id_proyecto, final CallBackResponse responses) {
        apiService.getSprints(id_proyecto).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful()) {
                    try {
                        //view.setRecycler(response.body().getMessage());
                        responses.onSuccess(response.body().getMessage(),"getSprints");
                    }catch (Exception e){
                        //view.showInfoMessage("No es posible consular la informacion");
                        responses.onnError("No es posible consular la informacion");
                    }
                    Log.i(TAG, "post submitted to API  222  " + response.body().getMessage().get(0).getNombre_pb());
                }else{
                    Log.e(TAG, "Unable to submit post to API." + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "Unable to get to API." + t.getMessage());
                //view.showInfoMessage("No es posible consular la informacion");
                responses.onnError("No es posible consular la informacion");
            }
        });
    }
}
