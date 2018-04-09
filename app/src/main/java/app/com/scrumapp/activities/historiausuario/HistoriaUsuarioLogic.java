package app.com.scrumapp.activities.historiausuario;

import android.util.Log;

import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class HistoriaUsuarioLogic implements HistoriaUsuarioILogic{

    private static HistoriaUsuarioLogic instance = null;
    DatabaseReference mFirebaseDatabaseReference;

    protected HistoriaUsuarioLogic(){}

    public static HistoriaUsuarioLogic getIntance(){
        if(instance == null){
            instance = new HistoriaUsuarioLogic();
        }

        return instance;
    }

    public void saveUserHistory(final HistoriadeUsuario hu) {
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference postRef= mFirebaseDatabaseReference.child("/HistoriadeUsuario/"+hu.getId());
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                HistoriadeUsuario p = mutableData.getValue(HistoriadeUsuario.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }

                p.setDesarrollador(hu.getDesarrollador());
                if(hu.getEstado()!=null || hu.getEstado().length()>0){
                    p.setEstado(hu.getEstado());
                }
                p.setAsignada(hu.isAsignada());
                p.setInformacionadicional(hu.getInformacionadicional());
                p.setTiempoTranscurrido(hu.getTiempoTranscurrido());
                p.setFechaInicio(hu.getFechaInicio());
                p.setFechaFin(hu.getFechaFin());
                p.setMotivocancelacion(hu.getMotivocancelacion());

                // p.setDescripcion(p.getDescripcion()+" "+descripcion);

                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }

        });
    }

    public HistoriadeUsuario getUserHistory(String idHu) {
        final HistoriadeUsuario[] returnHistoriaUsuario = {null};

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("HistoriadeUsuario");

        mFirebaseDatabaseReference.orderByChild("id_hu").equalTo(Integer.parseInt(idHu)).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                returnHistoriaUsuario[0] = dataSnapshot.getValue(HistoriadeUsuario.class);
                returnHistoriaUsuario[0].setId(Integer.parseInt(dataSnapshot.getKey()));
                //HistoriadeUsuario hu = dataSnapshot.getValue(HistoriadeUsuario.class);
                //hu.setId(Integer.parseInt(dataSnapshot.getKey()));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                returnHistoriaUsuario[0] = dataSnapshot.getValue(HistoriadeUsuario.class);
                returnHistoriaUsuario[0].setId(Integer.parseInt(dataSnapshot.getKey()));
                //HistoriadeUsuario hu = dataSnapshot.getValue(HistoriadeUsuario.class);
                //hu.setId(Integer.parseInt(dataSnapshot.getKey()));
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

        });

        return returnHistoriaUsuario[0];
    }

    public ArrayList<Usuario> getUsers() {
        final ArrayList<Usuario> usuarios = null;

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("Usuario");
        mFirebaseDatabaseReference.orderByChild("nombre").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                Log.e("Usuarios", usuario.toString());
                Log.e("Usuarios", "dato " +s);

                usuarios.add(usuario);
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
        });

        return usuarios;
    }
}