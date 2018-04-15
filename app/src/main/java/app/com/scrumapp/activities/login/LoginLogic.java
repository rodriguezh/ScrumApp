package app.com.scrumapp.activities.login;

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
    public void validateUser(String email, String password) {

    }
}
