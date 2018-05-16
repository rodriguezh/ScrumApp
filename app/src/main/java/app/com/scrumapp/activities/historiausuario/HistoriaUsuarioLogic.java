package app.com.scrumapp.activities.historiausuario;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import app.com.scrumapp.Constants;
import app.com.scrumapp.activities.MainActivity;
import app.com.scrumapp.data.model.Userlogin;
import app.com.scrumapp.data.remote.retrofit.APIServiceSprintBacklog;
import app.com.scrumapp.data.remote.retrofit.APIServiceUsers;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class HistoriaUsuarioLogic implements HistoriaUsuarioILogic{

    private static HistoriaUsuarioLogic instance = null;
    private final FirebaseFirestore db;
    private APIServiceSprintBacklog apiService;
    private APIServiceUsers apiServiceUsers;
    private DatabaseReference mFirebaseDatabaseReference;

    protected HistoriaUsuarioLogic(){
        apiService = ApiUtils.getAPIService(Constants.BASE_URLSPRINTB);
        apiServiceUsers= ApiUtils.getAPIServiceUsers(Constants.BASEURLLOGIN);
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

    @Override
    public void createUserHistory(HistoriadeUsuario hu, final CallBackResponse response) {
        hu.setTiempoEstimado(((MainActivity.dias*8)*hu.getEsfuerzo())/MainActivity.peso);
        Map<String,Object> map= hu.toMap();
        Usuario usuario=hu.getDesarrollador();
        usuario.setRol("Developer");
        map.put("desarrollador",usuario.toMap());
        db.collection("HistoriadeUsuario").add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        response.onSuccess("Historia creada","createUserHistory");
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        response.onSuccess("Historia no creada","createUserHistory");
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void getUserHistory(String idHu, final CallBackResponse response) {
        db.collection("HistoriadeUsuario").document(idHu).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "listen:error", e);
                    return;
                }
                HistoriadeUsuario hu;
                hu = documentSnapshot.toObject(HistoriadeUsuario.class);
                hu.setId(documentSnapshot.getId());
                response.onSuccess(hu, "getUserHistory");
                Log.d(TAG, "oe " + documentSnapshot.toObject(HistoriadeUsuario.class).toString());
            }});

    }

    public void getUsers(final CallBackResponse callresponse) {

        apiServiceUsers.getUsers("macs6367","1").enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
               // ArrayList<Usuario> usuarios1=((ArrayList<Usuario>) response.body());

                callresponse.onSuccess(response.body(),"getUsers");

            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {

            }
        });

    }
}