package app.com.scrumapp.data.remote.retrofit;

import java.util.ArrayList;

import app.com.scrumapp.models.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServiceUsers {

    @GET("admin/service_developers/")
    Call<ArrayList<Usuario>> getUsers(@Query("usuario") String usuario, @Query("proyecto") String proyecto);
}
