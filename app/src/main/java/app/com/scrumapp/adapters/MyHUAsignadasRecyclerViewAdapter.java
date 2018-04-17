package app.com.scrumapp.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.ObservableSnapshotArray;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.activities.historiausuario.HistoriaUsuarioActivity;
import app.com.scrumapp.fragments.huasignada.HUAsignadasFragment.OnListFragmentInteractionListener;
import app.com.scrumapp.models.HistoriadeUsuario;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHUAsignadasRecyclerViewAdapter extends FirestoreRecyclerAdapter<HistoriadeUsuario,MyHUAsignadasRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    public MyHUAsignadasRecyclerViewAdapter(FirestoreRecyclerOptions<HistoriadeUsuario> options, OnListFragmentInteractionListener mListener) {
        super(options);
        //modelClass, modelLayout, viewHolderClass, ref
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ObservableSnapshotArray<HistoriadeUsuario> getSnapshots() {
        return super.getSnapshots();
    }

    /**
     *  DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
     snapshot.getId();
     */
    @Override
    public void startListening() {
        super.startListening();
    }

    @Override
    public void stopListening() {
        super.stopListening();
    }

    @Override
    protected void onBindViewHolder(final ViewHolder viewHolder, final int position, HistoriadeUsuario model) {
        viewHolder.mItem=model;
        Log.e("---------->",model.toString());
        viewHolder.txtHistoria.setText("Historia de Usuario No "+model.getId_hu()+"");
        viewHolder.txtprioridad.setText("Prioridad "+model.getPrioridad());
        viewHolder.txtProyecto.setText("Protecto No "+model.getId_proyecto());
        viewHolder.txtSprint.setText("Sprint No "+model.getId_sprint());
        viewHolder.txtDesarrollador.setText("Desarrollador " +((model.getDesarrollador()!=null)?model.getDesarrollador().getNombre():"No asignado"));
        viewHolder.txtTiempo.setText("Tiempo transcurrido " +model.getTiempoTranscurrido());
        viewHolder.txtEstado.setText("Estado "+model.getEstado());
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento= new Intent(v.getContext(),HistoriaUsuarioActivity.class);

                intento.putExtra(Constants.IDHU,getSnapshots().getSnapshot(viewHolder.getAdapterPosition()).getId());
                //Log.e("IDHU","no"+viewHolder.mItem.getIdHU());
                v.getContext().startActivity(intento);
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(viewHolder.mItem);
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_historiausuario, parent, false);
        return new ViewHolder(view);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtHistoria;
        public final TextView txtprioridad;
        public final TextView txtProyecto;
        public final TextView txtSprint;
        public final TextView txtDesarrollador;
        public final TextView txtTiempo;
        public final TextView txtEstado;
        public HistoriadeUsuario mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtHistoria = (TextView) view.findViewById(R.id.txtHistoria);
            txtprioridad = (TextView) view.findViewById(R.id.txtprioridad);
            txtProyecto = (TextView) view.findViewById(R.id.txtProyecto);
            txtSprint = (TextView) view.findViewById(R.id.txtSprint);
            txtDesarrollador = (TextView) view.findViewById(R.id.txtDesarrollador);
            txtTiempo = (TextView) view.findViewById(R.id.txttiempo);
            txtEstado = (TextView) view.findViewById(R.id.txtEstado);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtHistoria.getText() + "'";
        }
    }
}