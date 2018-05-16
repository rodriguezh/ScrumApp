package app.com.scrumapp.activities.historiausuario;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

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
    }

    @Override
    public void createUserHistory(HistoriadeUsuario hu) {
        logicHistoria.createUserHistory(hu,this);
    }

    @Override
    public void getUserHistory(String idHu) {
       logicHistoria.getUserHistory(idHu,this);

    }

    @Override
    public void getUsers() {
       logicHistoria.getUsers(this);
        //mProfileView.setSpinnerUser(usuarios);
    }

    @Override
    public int timetoSeg(String chronoText) {

            int stoppedMilliseconds = 0;

            String array[] = chronoText.split(":");
            if (array.length == 2) {
                stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
                        + Integer.parseInt(array[1]) * 1000;
            } else if (array.length == 3) {
                stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                        + Integer.parseInt(array[1]) * 60 * 1000
                        + Integer.parseInt(array[2]) * 1000;
            }

        return stoppedMilliseconds/1000;


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
            case "createUserHistory":
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
