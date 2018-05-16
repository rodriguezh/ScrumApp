package app.com.scrumapp.data.remote.retrofit;

import java.util.ArrayList;

import app.com.scrumapp.data.model.SprintBacklogResponse;
import app.com.scrumapp.data.model.Userlogin;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServiceLogin {

    @GET("admin/service_login/")
    Call<ArrayList<Userlogin>> loginUser(@Query("usuario") String usuario, @Query("clave") String clave);

    @GET("admin/service_loginoff/")
    Call<Object> loginOff(@Query("usuario") String usuario,@Query("estado") String estado);

}
