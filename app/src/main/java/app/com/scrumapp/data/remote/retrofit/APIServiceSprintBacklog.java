package app.com.scrumapp.data.remote.retrofit;

import app.com.scrumapp.data.model.ApiResponse;
import app.com.scrumapp.data.model.SprintBacklogResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIServiceSprintBacklog {

    @GET("/pls/apex/modulo_sprint/prsp/{id_pb}")
    Call<SprintBacklogResponse> getHistoriasUsuarioIni(@Path("id_pb") int id_pg);


    @GET("/pls/apex/modulo_sprint/prsp/enc")
    Call<Object> getProductBacklog();

    @GET("/wst_projects_scrum-0.0.1-SNAPSHOT/scrum/api/sprints/{id_proyecto}")
    Call<ApiResponse> getSprints(@Path("id_proyecto") int id_proyecto);


    @GET("/wst_projects_scrum-0.0.1-SNAPSHOT/scrum/api/historias/{id_proyecto}/{id_sprint}")
    Call<SprintBacklogResponse> getHistorias(@Path("id_proyecto") int id_proyecto,
                                   @Path("id_sprint") int id_sprint);
}