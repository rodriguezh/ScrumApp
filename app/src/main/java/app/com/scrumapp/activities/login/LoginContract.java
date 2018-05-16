package app.com.scrumapp.activities.login;

import android.content.Context;

import app.com.scrumapp.BasePresenter;
import app.com.scrumapp.BaseView;
import app.com.scrumapp.data.model.Userlogin;
import app.com.scrumapp.models.Usuario;

public interface LoginContract {

    interface View extends BaseView<LoginContract.Presenter>{

        void keepDataLoggedUser(Userlogin objUsuario);

    }

    interface Presenter extends BasePresenter{

        void validateUser(String email, String password);
        void logOut(Context context);
    }
}
