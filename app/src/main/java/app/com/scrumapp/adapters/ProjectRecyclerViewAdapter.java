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
import app.com.scrumapp.activities.Sprints.MainSprintsActivity;
import app.com.scrumapp.activities.proyectos.ProjectsFragment;
import app.com.scrumapp.models.Proyecto;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 *
 * TODO: Replace the implementation with code for your data type.
 */
public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    private final List<Proyecto> mValues;
    private final ProjectsFragment.OnListFragmentInteractionListener mListener;
    public ProjectRecyclerViewAdapter(List<Proyecto> items, ProjectsFragment.OnListFragmentInteractionListener mListener) {
        mValues = items;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txtProyecto.setText(mValues.get(position).getNombre_proyecto());
        holder.txtEstado.setText(mValues.get(position).getEstado().getEstado());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(v.getContext(),MainSprintsActivity.class);
                intent.putExtra(Constants.IDPROYECTO,holder.mItem.getId());
                v.getContext().startActivity(intent);*/
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtProyecto;
        public final TextView txtEstado;
        public Proyecto mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtProyecto = (TextView) view.findViewById(R.id.nameProject);
            txtEstado = (TextView) view.findViewById(R.id.stateProject);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
