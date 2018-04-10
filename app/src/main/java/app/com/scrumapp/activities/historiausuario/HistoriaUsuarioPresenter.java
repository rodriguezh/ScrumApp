package app.com.scrumapp.activities.historiausuario;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.com.scrumapp.Constants;
import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;

import static android.content.ContentValues.TAG;

public class HistoriaUsuarioPresenter implements HistoriaUsuarioContract.Presenter {

    @NonNull
    private final HistoriaUsuarioContract.View mProfileView;
    @NonNull
    private String idHu;

    private ArrayList<Usuario> usuarios;

    DatabaseReference mFirebaseDatabaseReference;

    private final FirebaseFirestore db;

    public HistoriaUsuarioPresenter(@NonNull HistoriaUsuarioContract.View mProfileView, @NonNull String idHu) {
        this.mProfileView = mProfileView;
        this.idHu = idHu;
        usuarios= new ArrayList<>();
        this.db = FirebaseFirestore.getInstance();
    }


    @Override
    public void saveUserHistory(final HistoriadeUsuario hu) {
        final DocumentReference postRef = db.collection("HistoriadeUsuario").document(hu.getId());
        Map<String,Object> map= hu.toMapUpdate();

        if(hu.getEstado()!=null && hu.getEstado().length()>0){
            map.put("estado", hu.getEstado());
            map.put("fechaFin", hu.getFechaFin());
            map.put("motivocancelacion", hu.getMotivocancelacion());
        }

        postRef.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mProfileView.showInfoMessage("Historia Actualizada");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mProfileView.showInfoMessage("No fue posible actualizar");
            }
        });
       /* mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
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
                mProfileView.showInfoMessage("Historia Actualizada");
                // Transaction completed
                 Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }

        });*/
    }

    @Override
    public void getUserHistory(String idHu) {
        Log.e("---->",idHu);
        db.collection("HistoriadeUsuario")
                .whereEqualTo("id_hu", Integer.parseInt(idHu))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "listen:error", e);
                            return;
                        }
                        HistoriadeUsuario hu;

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                     hu = dc.getDocument().toObject(HistoriadeUsuario.class);
                                    hu.setId(dc.getDocument().getId());

                                    if (hu.getTiempoTranscurrido()!=null){
                                        tiempoCronometro(hu.getTiempoTranscurrido());
                                    }
                                    mProfileView.loadView(hu);
                                    Log.d(TAG,  "oe "+dc.getDocument().toObject(HistoriadeUsuario.class).toString());
                                    break;
                                case MODIFIED:
                                     hu = dc.getDocument().toObject(HistoriadeUsuario.class);
                                    hu.setId(dc.getDocument().getId());

                                    if (hu.getTiempoTranscurrido()!=null){
                                        tiempoCronometro(hu.getTiempoTranscurrido());
                                    }
                                    mProfileView.loadView(hu);
                                    Log.d(TAG, "Modified : " + dc.getDocument().toObject(HistoriadeUsuario.class).toString());
                                    break;
                                case REMOVED:
                                    Log.d(TAG, "Removed city: " + dc.getDocument().getData());
                                    break;
                            }
                        }

                    }
                });
    }

    @Override
    public void getUsers() {

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("Usuario");
        mFirebaseDatabaseReference.orderByChild("nombre").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                Log.e("Usuarios", usuario.toString());
                Log.e("Usuarios", "dato " +s);
                usuarios.add(usuario);
                mProfileView.setSpinnerUser(usuarios);
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
