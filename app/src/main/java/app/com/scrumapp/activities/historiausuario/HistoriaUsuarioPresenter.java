package app.com.scrumapp.activities.historiausuario;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;

import static android.content.ContentValues.TAG;

public class HistoriaUsuarioPresenter implements HistoriaUsuarioContract.Presenter {

    @NonNull
    private final HistoriaUsuarioContract.View mProfileView;
    @NonNull
    private String idHu;

    private ArrayList<Usuario> usuarios;

    HistoriaUsuarioLogic logicHistoria = HistoriaUsuarioLogic.getIntance();

    public HistoriaUsuarioPresenter(@NonNull HistoriaUsuarioContract.View mProfileView, @NonNull String idHu) {
        this.mProfileView = mProfileView;
        this.idHu = idHu;
        usuarios= new ArrayList<>();
    }

    @Override
    public void saveUserHistory(final HistoriadeUsuario hu) {
        logicHistoria.saveUserHistory(hu);

        mProfileView.showInfoMessage("Historia Actualizada");
    }

    @Override
    public void getUserHistory(String idHu) {
        HistoriadeUsuario historiaUsuario = logicHistoria.getUserHistory(idHu);

        if (historiaUsuario.getTiempoTranscurrido()!=null){
            tiempoCronometro(historiaUsuario.getTiempoTranscurrido());
        }
        mProfileView.loadView(historiaUsuario);
    }

    @Override
    public void getUsers() {
        usuarios = logicHistoria.getUsers();

        mProfileView.setSpinnerUser(usuarios);
    }

    @Override
    public void start() {
        getUserHistory(idHu);
        getUsers();

    }

    private void tiempoCronometro(String tiempoTranscurrido){
        String[] parts = tiempoTranscurrido.split(":");
        int mm=0, ss=0;
        if (parts.length==3){
            mm=Integer.parseInt(parts[1])+(Integer.parseInt(parts[0])*60);
            ss=Integer.parseInt(parts[2]);
        }else{
            mm=Integer.parseInt(parts[0]);
            ss=Integer.parseInt(parts[1]);
        }
        mProfileView.setTimeChronometer(mm,ss);

    }
}
