package app.com.scrumapp.activities.historiausuario;

import java.util.ArrayList;

import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;

public interface HistoriaUsuarioILogic{

    void saveUserHistory(HistoriadeUsuario hu);

    HistoriadeUsuario getUserHistory(String idHu);

    ArrayList<Usuario> getUsers();
}