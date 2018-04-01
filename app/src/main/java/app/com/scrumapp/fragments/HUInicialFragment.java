package app.com.scrumapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.com.scrumapp.adapters.MyHistoriaUsuarioRecyclerViewAdapter;
import app.com.scrumapp.R;

import app.com.scrumapp.models.HistoriadeUsuario;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HUInicialFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private DatabaseReference mFirebaseDatabaseReference;
    RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HUInicialFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HUInicialFragment newInstance(int columnCount) {
        HUInicialFragment fragment = new HUInicialFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historiausuario_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            /* recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyHistoriaUsuarioRecyclerViewAdapter( HistoriadeUsuario.class,
                    R.layout.fragment_historiausuario,
                    MyHistoriaUsuarioRecyclerViewAdapter.ViewHolder.class,
                    mFirebaseDatabaseReference.child("HistoriasdeUsuario")));

*/
            recyclerView = (RecyclerView) view;
            mLinearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(mLinearLayoutManager);

            mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
            recyclerView.setAdapter(new MyHistoriaUsuarioRecyclerViewAdapter( HistoriadeUsuario.class,
                    R.layout.fragment_historiausuario,
                    MyHistoriaUsuarioRecyclerViewAdapter.ViewHolder.class,
                    mFirebaseDatabaseReference.child("HistoriaUsuarioInicial"), mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(HistoriadeUsuario item);
    }
}
