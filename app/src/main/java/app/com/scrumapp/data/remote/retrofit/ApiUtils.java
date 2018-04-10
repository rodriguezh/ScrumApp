package app.com.scrumapp.data.remote.retrofit;

public class ApiUtils {

    private ApiUtils() {}


    public static APIServiceSprintBacklog getAPIService(String baseUrl) {

        return RetrofitClient.getClient(baseUrl).create(APIServiceSprintBacklog.class);
    }
}