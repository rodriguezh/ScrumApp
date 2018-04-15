package app.com.scrumapp.activities.login;

import android.support.annotation.NonNull;

import app.com.scrumapp.models.Usuario;

public class LoginPresenter implements LoginContract.Presenter, CallBackResponse {

    private static LoginPresenter instance = null;
    @NonNull
    private final LoginContract.View mProfileView;

    protected LoginPresenter(@NonNull LoginContract.View mProfileView) {
        this.mProfileView = mProfileView;
    }

    public static LoginPresenter getInstance(@NonNull LoginContract.View mProfileView){
        if(instance == null){
            instance = new LoginPresenter(mProfileView);
        }

        return instance;
    }

    @Override
    public void start() {

    }

    @Override
    public void onSuccess(Object object, String nameMethod) {
        switch (nameMethod){
            case "validateUser":
                mProfileView.keepDataLoggedUser(((Usuario)object));
                break;
        }
    }

    @Override
    public void onnError(Object object) {

    }

    @Override
    public void validateUser(String email, String password) {
    }
}
