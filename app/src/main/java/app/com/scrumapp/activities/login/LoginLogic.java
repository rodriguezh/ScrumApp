package app.com.scrumapp.activities.login;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.remote.retrofit.APIServiceLogin;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import app.com.scrumapp.models.Usuario;

public class LoginLogic implements LoginILogic {

    private static LoginLogic instance = null;
    private APIServiceLogin apiService;

    protected LoginLogic(){
        apiService = ApiUtils.getAPIServiceLogin(Constants.BASE_URLLOGIN);
    }

    public static LoginLogic getInstance(){
        if(instance == null){
            instance = new LoginLogic();
        }

        return instance;
    }

    @Override
    public void validateUser(String email, String password, CallBackResponse response) {
        Usuario objUsuario = new Usuario("000011", "Fulano", "Desarrollador");
        objUsuario.setEmail(email);
        objUsuario.setPassword(password);
        objUsuario.setIdProyecto(1);

        response.onSuccess(objUsuario, "validateUser");
    }
}
