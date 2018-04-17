package app.com.scrumapp.data.remote.retrofit;

import app.com.scrumapp.data.model.SprintBacklogResponse;
import app.com.scrumapp.data.model.SprintProjectResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Fernando on 15/04/2018.
 */

public interface APIServiceProjects {
//http://pbacklog-pbacklog.1d35.starter-us-east-1.openshiftapps.com/public/api/proyectos
    @GET("/wst_projects_scrum-0.0.1-SNAPSHOT/scrum/api/public/api/proyectos")
    Call<SprintProjectResponse> getProyectos();


}
