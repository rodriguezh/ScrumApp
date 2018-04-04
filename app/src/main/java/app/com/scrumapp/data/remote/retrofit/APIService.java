package app.com.scrumapp.data.remote.retrofit;

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

public interface APIService {

    @POST("/API_CTS/user")
    @FormUrlEncoded
    rx.Observable<HistoriadeUsuario> saveUser(@Field("pege_id") int pege_id,
                                              @Field("identificacion") String identificacion,
                                              @Field("nombre") String nombre,
                                              @Field("estudios") String estudios,
                                              @Field("publicaciones") String publicaciones,
                                              @Field("experiencia") String experiencia,
                                              @Field("correo") String correo,
                                              @Field("url_foto") String url_foto,
                                              @Field("tipo") String tipo,
                                              @Field("carrera") String carrera
    );

    @PUT("/API_CTS/user/{pege_id}")
    Call<ApiResponse> updateUser(@Path("pege_id") int pege_id,
                                 @Body HistoriadeUsuario usuario);

    @GET("/API_CTS/user/{pege_id}")
    Call<HistoriadeUsuario> getUser(@Path("pege_id") int pege_id);

    @PUT("/API_CTS/user/registerclass/:codigo")
    @FormUrlEncoded
    Call<HistoriadeUsuario> registerUser(@Path("codigo") int codigoInscripcion,
                               @Field("pege_id") int pege_id);


    @DELETE("/API_CTS/class/{id_clase}")
    Call<ApiResponse> deleteClass(@Path("id_clase") String _idclase);




}