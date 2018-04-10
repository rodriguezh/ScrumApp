package app.com.scrumapp.data.remote.retrofit;

import app.com.scrumapp.data.model.ApiResponse;
import app.com.scrumapp.data.model.HistoriadeUsuarioInicial;
import app.com.scrumapp.data.model.SprintBacklogResponse;
import app.com.scrumapp.models.HistoriadeUsuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIServiceSprintBacklog {

    @GET("/pls/apex/modulo_sprint/prsp/{id_pb}")
    Call<SprintBacklogResponse> getHistoriasUsuarioIni(@Path("id_pb") int id_pg);


    @GET("/pls/apex/modulo_sprint/prsp/enc")
    Call<Object> getProductBacklog();

}