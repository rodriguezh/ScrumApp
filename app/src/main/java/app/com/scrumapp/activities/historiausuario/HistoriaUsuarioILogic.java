package app.com.scrumapp.activities.historiausuario;

import app.com.scrumapp.models.HistoriadeUsuario;


public interface HistoriaUsuarioILogic{

    void saveUserHistory(HistoriadeUsuario hu, CallBackResponse response);
    void createUserHistory(HistoriadeUsuario hu, CallBackResponse response);
    void getUserHistory(String idHu, CallBackResponse response);

    void getUsers(CallBackResponse response);
}