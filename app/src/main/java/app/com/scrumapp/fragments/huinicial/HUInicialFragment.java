package app.com.scrumapp.fragments.huinicial;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

import app.com.scrumapp.adapters.MyHistoriaUsuarioRecyclerViewAdapter;
import app.com.scrumapp.R;

import app.com.scrumapp.data.model.HistoriadeUsuarioInicial;
import app.com.scrumapp.data.model.SprintBacklogResponse;
import app.com.scrumapp.data.remote.retrofit.APIServiceSprintBacklog;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import app.com.scrumapp.models.HistoriadeUsuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HUInicialFragment extends Fragment implements HUInicialContract.View {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private MyHistoriaUsuarioRecyclerViewAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    private HUInicialContract.Presenter mPresenter;
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      mPresenter= new HUInicialPresenter(this,1,1);
        recyclerView = (RecyclerView)getActivity().findViewById(R.id.listhui);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historiausuario_list, container, false);

        return view;
    }
    private APIServiceSprintBacklog apiService;
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();

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
    public void loadView(List<HistoriadeUsuarioInicial> list) {
        Log.e("--->",list.toString());
        adapter=new MyHistoriaUsuarioRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(HUInicialContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);
    }

    @Override
    public void showInfoMessage(String respuesta) {

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
