package app.com.scrumapp.activities.login;

public interface CallBackResponse {
    void onSuccess(Object object, String nameMethod);
    void onnError(Object object);
}
