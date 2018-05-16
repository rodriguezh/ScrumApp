package app.com.scrumapp.activities.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import app.com.scrumapp.activities.MainActivity;
import app.com.scrumapp.data.model.Userlogin;
import app.com.scrumapp.models.Usuario;

public class LoginPresenter implements LoginContract.Presenter, CallBackResponse {

    private static LoginPresenter instance = null;
    @NonNull
    private final LoginContract.View mProfileView;
    LoginLogic logic = LoginLogic.getInstance();


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
    public void onSuccess(Object object, String nameMethod, Context context) {
        switch (nameMethod){
            case "validateUser":
                mProfileView.keepDataLoggedUser(((Userlogin)object));
                break;
                case "loginOff":
                   // mProfileView.logOut();
                   // mProfileView.showInfoMessage(object.toString());
                    Toast.makeText(context,object.toString(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,LoginActivity.class);
                    //borrar  el historial apenas salga de la sesión por seguridad
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    break;
        }
    }

    @Override
    public void onnError(Object object) {
        mProfileView.showInfoMessage(object.toString());
    }

    @Override
    public void validateUser(String email, String password) {
        logic.validateUser(email, password, this);
    }

    @Override
    public void logOut(Context context) {
        logic.logignOff(this,context);
    }
}
