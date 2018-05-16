package app.com.scrumapp.fragments.huasignada;

import android.support.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import app.com.scrumapp.Constants;
import app.com.scrumapp.models.HistoriadeUsuario;

/**
 * Created by Fernando on 16/04/2018.
 */

public class HUAsignadaPresenter implements HUAsignadaContract.Presenter {

    @NonNull
    private final HUAsignadaContract.View mView;
    @NonNull
    private int id_pb, id_sprint;

    private Query query;

    public HUAsignadaPresenter(@NonNull HUAsignadaContract.View mView, @NonNull int id_pb, @NonNull int id_sprint) {
        this.mView = mView;
        this.id_pb = id_pb;
        this.id_sprint = id_sprint;
         query = FirebaseFirestore.getInstance()
                .collection(Constants.COLLECTIONHISTORIAUSUARIO).whereEqualTo("id_proyecto",id_pb).whereEqualTo("id_sprint",id_sprint);

    }

    @Override
    public void start() {
        getUsesrHistory(id_pb,id_sprint);
    }

    @Override
    public void getUsesrHistory(int id_pb, int id_sprint) {
        FirestoreRecyclerOptions<HistoriadeUsuario> options =
                new FirestoreRecyclerOptions.Builder<HistoriadeUsuario>()
                        .setQuery(query, HistoriadeUsuario.class)
                        .build();
        mView.loadView(options);
    }
}
