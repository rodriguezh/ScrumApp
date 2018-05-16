package app.com.scrumapp.activities.Sprints;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.adapters.SprintsRecyclerViewAdapter;
import app.com.scrumapp.models.Sprint;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainSprintFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainSprintFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainSprintFragment extends Fragment implements MainSprintsContract.View {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    MainSprintsContract.Presenter mPresenter;
    private int id_proyecto;
    private SprintsRecyclerViewAdapter mySprintRecyclerViewAdapter;
    private RecyclerView recyclerView;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainSprintFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if(getArguments().getInt(Constants.IDPROYECTO)>0){
            id_proyecto=getArguments().getInt(Constants.IDPROYECTO);
            mPresenter= new MainSprintsPresenter(this,id_proyecto);
        }

        recyclerView = (RecyclerView)getActivity().findViewById(R.id.listSprints);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void setPresenter(MainSprintsContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
    @Override
    public void showInfoMessage(String respuesta) {

    }

    @Override
    public void setRecycler(List<Sprint> sprints) {
        mySprintRecyclerViewAdapter=new SprintsRecyclerViewAdapter(sprints, mListener);
        recyclerView.setAdapter(mySprintRecyclerViewAdapter);
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainSprintFragment.
     */

    public static MainSprintFragment newInstance(String param1, String param2) {
        MainSprintFragment fragment = new MainSprintFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_sprint, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Sprint sprint);
    }
}
