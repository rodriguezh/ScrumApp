package app.com.scrumapp.activities.historiausuario;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;

public class HistoriaUsuarioPresenter implements HistoriaUsuarioContract.Presenter, CallBackResponse {

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

        logicHistoria.saveUserHistory(hu,this);

       // mProfileView.showInfoMessage("Historia Actualizada");

    }

    @Override
    public void getUserHistory(String idHu) {

        Log.e("---->",idHu);

       logicHistoria.getUserHistory(idHu,this);

       /* if (historiaUsuario.getTiempoTranscurrido()!=null){
            tiempoCronometro(historiaUsuario.getTiempoTranscurrido());
        }
        mProfileView.loadView(historiaUsuario);*/

    }

    @Override
    public void getUsers() {
       logicHistoria.getUsers(this);

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

    @Override
    public void onSuccess(Object object, String nameMethod) {
        switch (nameMethod){
            case "saveUserHistory":
                mProfileView.showInfoMessage(object.toString());
                break;
            case "getUserHistory":
                if (((HistoriadeUsuario)object).getTiempoTranscurrido()!=null){
                     tiempoCronometro(((HistoriadeUsuario)object).getTiempoTranscurrido());
                }
                mProfileView.loadView(((HistoriadeUsuario)object));
                break;
            case "getUsers":
                mProfileView.setSpinnerUser((ArrayList<Usuario>)object);
                break;
        }
    }

    @Override
    public void onnError(Object object) {

    }
}
