package app.com.scrumapp.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.activities.historiausuario.HistoriaUsuarioActivity;
import app.com.scrumapp.fragments.HUInicialFragment.OnListFragmentInteractionListener;

import app.com.scrumapp.models.HistoriadeUsuario;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHistoriaUsuarioRecyclerViewAdapter extends FirebaseRecyclerAdapter<HistoriadeUsuario,MyHistoriaUsuarioRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    public MyHistoriaUsuarioRecyclerViewAdapter(Class<HistoriadeUsuario> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, DatabaseReference ref, OnListFragmentInteractionListener mListener) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mListener = mListener;
    }

   /* private final List<HistoriadeUsuario> mValues;
    private final OnListFragmentInteractionListener mListener;

   /* public MyHistoriaUsuarioRecyclerViewAdapter(List<HistoriadeUsuario> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }*/

    /*@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_historiausuario, parent, false);
        return new ViewHolder(view);
    }*/



    @Override
    protected HistoriadeUsuario parseSnapshot(DataSnapshot snapshot) {
        HistoriadeUsuario friendlyMessage = super.parseSnapshot(snapshot);
        if (friendlyMessage != null) {
            friendlyMessage.set_id(snapshot.getKey()+"");
        }
        return friendlyMessage;
    }

    @Override
    protected void populateViewHolder( final ViewHolder viewHolder, HistoriadeUsuario model, int position) {
        viewHolder.mItem=model;
        viewHolder.mIdView.setText(model.getNombreHu());
        viewHolder.mIdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento= new Intent(v.getContext(),HistoriaUsuarioActivity.class);
                intento.putExtra(Constants.IDHU,viewHolder.mItem.getPrioridad()+"");
                Log.e("IDHU","no"+viewHolder.mItem.getIdHU());
                v.getContext().startActivity(intento);
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(viewHolder.mItem);
                }
            }
        });
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
        public final TextView mIdView;
        public final TextView mContentView;
        public HistoriadeUsuario mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
