package app.com.scrumapp.activities.historiausuario;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.com.scrumapp.models.HistoriadeUsuario;

import static android.content.ContentValues.TAG;

public class HistoriaUsuarioPresenter implements HistoriaUsuarioContract.Presenter {

    @NonNull
    private final HistoriaUsuarioContract.View mProfileView;
    @NonNull
    private String idHu;

    DatabaseReference mFirebaseDatabaseReference;

    public HistoriaUsuarioPresenter(@NonNull HistoriaUsuarioContract.View mProfileView, @NonNull String idHu) {
        this.mProfileView = mProfileView;
        this.idHu = idHu;
         mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("HistoriaUsuarioInicial");
    }


    @Override
    public void saveUserHistory(int Sprint, String criteriosAceptacion, String descripcion, int idHu, String nombreHu, int peso, int prioridad) {

    }

    @Override
    public void getUserHistory(String idHu) {
        Log.i(TAG, "------------------------->" + idHu);
        mFirebaseDatabaseReference.orderByChild("idHu").equalTo(Integer.parseInt(idHu)).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
               HistoriadeUsuario hu = dataSnapshot.getValue(HistoriadeUsuario.class);
                System.out.println(dataSnapshot.getKey() + " ------------------------->" + hu.getDescripcion()+ "");
                //Log.i(TAG, "------------------------->" +hu.getDescripcion() );
                mProfileView.loadView(hu);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            // ...
        });
    }

    @Override
    public void start() {
        getUserHistory(idHu);
    }
}
