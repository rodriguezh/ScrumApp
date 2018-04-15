package app.com.scrumapp.activities.login;

import app.com.scrumapp.models.Usuario;

public class LoginLogic implements LoginILogic {

    private static LoginLogic instance = null;

    protected LoginLogic(){}

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

        response.onSuccess(objUsuario, "validateUser");
    }
}
