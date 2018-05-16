package app.com.scrumapp.activities.login;

import android.content.Context;

public interface CallBackResponse {
    void onSuccess(Object object, String nameMethod, Context context);
    void onnError(Object object);
}
