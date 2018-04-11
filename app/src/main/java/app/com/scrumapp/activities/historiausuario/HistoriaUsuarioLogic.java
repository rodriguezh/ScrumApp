package app.com.scrumapp.activities.historiausuario;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import app.com.scrumapp.Constants;
import app.com.scrumapp.data.remote.retrofit.APIServiceSprintBacklog;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;

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
import java.util.Map;

import static android.content.ContentValues.TAG;

public class HistoriaUsuarioLogic implements HistoriaUsuarioILogic{

    private static HistoriaUsuarioLogic instance = null;
    private final FirebaseFirestore db;
    private APIServiceSprintBacklog apiService;
    private DatabaseReference mFirebaseDatabaseReference;

    protected HistoriaUsuarioLogic(){
        apiService = ApiUtils.getAPIService(Constants.BASE_URLSPRINTB);
        this.db = FirebaseFirestore.getInstance();
    }

    public static HistoriaUsuarioLogic getIntance(){
        if(instance == null){
            instance = new HistoriaUsuarioLogic();
        }

        return instance;
    }

    public void saveUserHistory(final HistoriadeUsuario hu, final CallBackResponse response) {
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
                //mProfileView.showInfoMessage("Historia Actualizada");
                response.onSuccess("Historia Actualizada","saveUserHistory");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //mProfileView.showInfoMessage("No fue posible actualizar");
            }
        });
    }

    public void getUserHistory(String idHu, final CallBackResponse response) {
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
                                    response.onSuccess(hu,"getUserHistory");
                                    if (hu.getTiempoTranscurrido()!=null){
                                       // tiempoCronometro(hu.getTiempoTranscurrido());
                                    }
                                  //  mProfileView.loadView(hu);
                                    Log.d(TAG,  "oe "+dc.getDocument().toObject(HistoriadeUsuario.class).toString());
                                    break;
                                case MODIFIED:
                                    hu = dc.getDocument().toObject(HistoriadeUsuario.class);
                                    hu.setId(dc.getDocument().getId());

                                    response.onSuccess(hu,"getUserHistory");
                                    if (hu.getTiempoTranscurrido()!=null){
                                       // tiempoCronometro(hu.getTiempoTranscurrido());

                                    }
                                    //mProfileView.loadView(hu);
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

    public void getUsers(final CallBackResponse response) {
        final ArrayList<Usuario> usuarios = new ArrayList<>();

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("Usuario");
        mFirebaseDatabaseReference.orderByChild("nombre").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                Log.e("Usuarios", usuario.toString());
                Log.e("Usuarios", "dato " +s);

                usuarios.add(usuario);
                response.onSuccess(usuarios,"getUsers");

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
}