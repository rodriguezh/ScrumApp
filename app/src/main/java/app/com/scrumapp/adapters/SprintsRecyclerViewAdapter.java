package app.com.scrumapp.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.activities.MainActivity;
import app.com.scrumapp.activities.Sprints.MainSprintFragment;
import app.com.scrumapp.models.Sprint;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 *
 */
public class SprintsRecyclerViewAdapter extends RecyclerView.Adapter<SprintsRecyclerViewAdapter.ViewHolder> {

    private final List<Sprint> mValues;
    private final MainSprintFragment.OnFragmentInteractionListener mListener;
    public SprintsRecyclerViewAdapter(List<Sprint> items, MainSprintFragment.OnFragmentInteractionListener mListener) {
        mValues = items;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sprint, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txtProyecto.setText(mValues.get(position).getId_sprint()+"");
       holder.txtEstado.setText(""+mValues.get(position).getEstado_st());
        holder.txtDias.setText(""+mValues.get(position).getDias_sprint());
        holder.txtPeso.setText(""+mValues.get(position).getPeso_total_pb());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(v.getContext(),MainActivity.class);
                intent.putExtra(Constants.IDSPRINT,holder.mItem.getId_sprint());
                intent.putExtra(Constants.IDPROYECTO,holder.mItem.getId_pb());
                v.getContext().startActivity(intent);*/
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtProyecto;
        public final TextView txtEstado;
        public final TextView txtDias;
        public final TextView txtPeso;
        public Sprint mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtProyecto = (TextView) view.findViewById(R.id.nameSprint);
            txtEstado = (TextView) view.findViewById(R.id.txtstate);
            txtDias = (TextView) view.findViewById(R.id.txtDias);
            txtPeso = (TextView) view.findViewById(R.id.txtPeso);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
