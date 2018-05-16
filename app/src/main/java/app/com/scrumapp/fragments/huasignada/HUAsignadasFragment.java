package app.com.scrumapp.fragments.huasignada;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.adapters.MyHUAsignadasRecyclerViewAdapter;
import app.com.scrumapp.models.HistoriadeUsuario;

import static android.content.Context.MODE_PRIVATE;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HUAsignadasFragment extends Fragment implements HUAsignadaContract.View{


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private DatabaseReference mFirebaseDatabaseReference;
    private RecyclerView recyclerView;
    private MyHUAsignadasRecyclerViewAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private HUAsignadaContract.Presenter mPresenter;
    private SharedPreferences sharedPreferences;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HUAsignadasFragment() {
    }


    @SuppressWarnings("unused")
    public static HUAsignadasFragment newInstance(int columnCount) {
        HUAsignadasFragment fragment = new HUAsignadasFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historiadeusuario_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        mLinearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        sharedPreferences = getActivity().getSharedPreferences("Preferences",MODE_PRIVATE);
        String rol=sharedPreferences.getString("rol", "");
        String identificacion=sharedPreferences.getString("id", "");
        //mPresenter= new HUAsignadaPresenter(this,getArguments().getInt(Constants.IDPROYECTO),getArguments().getInt(Constants.IDSPRINT));
        Query query;
        if (rol.equals("Developer")){
            query = FirebaseFirestore.getInstance()
                    .collection(Constants.COLLECTIONHISTORIAUSUARIO).whereEqualTo("id_proyecto",getArguments().getInt(Constants.IDPROYECTO)).whereEqualTo("id_sprint",getArguments().getInt(Constants.IDSPRINT))
                   .whereEqualTo("desarrollador.identificacion",identificacion);
        }else{
            query = FirebaseFirestore.getInstance()
                     .collection(Constants.COLLECTIONHISTORIAUSUARIO).whereEqualTo("id_proyecto",getArguments().getInt(Constants.IDPROYECTO)).whereEqualTo("id_sprint",getArguments().getInt(Constants.IDSPRINT));
                    //.collection(Constants.COLLECTIONHISTORIAUSUARIO).whereEqualTo("desarrollador.nombre","Karen");
        }



        FirestoreRecyclerOptions<HistoriadeUsuario> options =
                new FirestoreRecyclerOptions.Builder<HistoriadeUsuario>()
                        .setQuery(query, HistoriadeUsuario.class)
                        .build();


        adapter=new MyHUAsignadasRecyclerViewAdapter( options, mListener);


        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
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

    @Override
    public void setPresenter(HUAsignadaContract.Presenter presenter) {

    }

    @Override
    public void showInfoMessage(String respuesta) {

    }

    @Override
    public void loadView(FirestoreRecyclerOptions<HistoriadeUsuario> options) {
        adapter=new MyHUAsignadasRecyclerViewAdapter( options, mListener);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
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
