package app.com.scrumapp.data.remote.retrofit;

public class ApiUtils {

    private ApiUtils() {}


    public static APIServiceSprintBacklog getAPIService(String baseUrl) {
        return RetrofitClient.getClient(baseUrl).create(APIServiceSprintBacklog.class);
    }

    public static APIServiceProjects getAPIServiceProject(String baseUrl) {
        return RetrofitClient.getClient(baseUrl).create(APIServiceProjects.class);
    }
    public static APIServiceLogin getAPIServiceLogin(String baseUrl) {
        return  RetrofitClient2.getClient2(baseUrl).create(APIServiceLogin.class);
    }

    public static APIServiceUsers getAPIServiceUsers(String baseUrl){
        return  RetrofitClient2.getClient2(baseUrl).create(APIServiceUsers.class);
    }

}