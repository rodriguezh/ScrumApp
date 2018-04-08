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

import java.util.List;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.activities.historiausuario.HistoriaUsuarioActivity;
import app.com.scrumapp.data.model.HistoriadeUsuarioInicial;
import app.com.scrumapp.fragments.huinicial.HUInicialFragment.OnListFragmentInteractionListener;

import app.com.scrumapp.models.HistoriadeUsuario;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHistoriaUsuarioRecyclerViewAdapter extends RecyclerView.Adapter<MyHistoriaUsuarioRecyclerViewAdapter.ViewHolder> {

    private final List<HistoriadeUsuarioInicial> mValues;

    public MyHistoriaUsuarioRecyclerViewAdapter(List<HistoriadeUsuarioInicial> items) {
        mValues = items;
    }



/*
    @Override
    protected HistoriadeUsuario parseSnapshot(DataSnapshot snapshot) {
        HistoriadeUsuario hu = super.parseSnapshot(snapshot);
        if (hu != null) {
            hu.setId(Integer.parseInt(snapshot.getKey()));
        }
        return hu;
    }

    @Override
    protected void populateViewHolder( final ViewHolder viewHolder, HistoriadeUsuario model, int position) {
        viewHolder.mItem=model;
        Log.e("---------->",model.toString());
        viewHolder.txtHistoria.setText("Historia de Usuario No "+model.getId_hu()+"");
        viewHolder.txtprioridad.setText("Prioridad "+model.getPrioridad());
        viewHolder.txtProyecto.setText("Protecto No "+model.getId_proyecto());
        viewHolder.txtSprint.setText("Sprint No "+model.getId_sprint());
        viewHolder.txtDesarrollador.setText("Desarrollador " +((model.getDesarrollador()!=null)?model.getDesarrollador().getNombre():"No asignado"));
        viewHolder.txtTiempo.setText("Tiempo estimado " +model.getTiempoEstimado());
        viewHolder.txtEstado.setText("Estado "+model.getEstado());
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento= new Intent(v.getContext(),HistoriaUsuarioActivity.class);
                intento.putExtra(Constants.IDHU,viewHolder.mItem.getId_hu()+"");
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
*/






    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_historiausuario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        viewHolder.mItem=mValues.get(position);

        viewHolder.txtHistoria.setText("Historia de Usuario No ");
        viewHolder.txtprioridad.setText("Prioridad "+mValues.get(position).getPrioridad_hu());
        viewHolder.txtProyecto.setText("Descripcion "+mValues.get(position).getNombre_hu());
        viewHolder.txtSprint.setText("Sprint No "+mValues.get(position).getId_sprint());
        viewHolder.txtDesarrollador.setText("Peso " +mValues.get(position).getPeso_hu());
        //  viewHolder.txtTiempo.setText("Tiempo estimado " +model.getTiempoEstimado());
        ///viewHolder.txtEstado.setText("Estado "+model.getEstado());
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento= new Intent(v.getContext(),HistoriaUsuarioActivity.class);
                intento.putExtra(Constants.IDHU,viewHolder.mItem.getId_sprint()+"");
                intento.putExtra(Constants.FORMTYPE,Constants.FORMASSIGN);
                //Log.e("IDHU","no"+viewHolder.mItem.getIdHU());
                v.getContext().startActivity(intento);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /* @Override
     public void onBindViewHolder(final ViewHolder holder, int position) {
         holder.mItem = mValues.get(position);
         holder.mIdView.setText(mValues.get(position).getNombreHu());
         holder.mContentView.setText(mValues.get(position).get_id());

         holder.mView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (null != mListener) {
                     // Notify the active callbacks interface (the activity, if the
                     // fragment is attached to one) that an item has been selected.
                     mListener.onListFragmentInteraction(holder.mItem);
                 }
             }
         });
     }

     @Override
     public int getItemCount() {
         return mValues.size();
     }
 */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtHistoria;
        public final TextView txtprioridad;
        public final TextView txtProyecto;
        public final TextView txtSprint;
        public final TextView txtDesarrollador;
        public final TextView txtTiempo;
        public final TextView txtEstado;
        public HistoriadeUsuarioInicial mItem;

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
