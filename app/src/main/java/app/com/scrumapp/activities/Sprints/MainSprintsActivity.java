package app.com.scrumapp.activities.Sprints;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.adapters.SprintsRecyclerViewAdapter;
import app.com.scrumapp.models.Sprint;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainSprintsActivity extends AppCompatActivity implements MainSprintsContract.View{

    MainSprintsContract.Presenter mPresenter;
    private int id_proyecto;
    private SprintsRecyclerViewAdapter mySprintRecyclerViewAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sprints);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();

        if(bundle.getInt(Constants.IDPROYECTO)>0){
         id_proyecto=bundle.getInt(Constants.IDPROYECTO);
            mPresenter= new MainSprintsPresenter(this,id_proyecto);
        }

        recyclerView = (RecyclerView)findViewById(R.id.listSprints);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void setPresenter(MainSprintsContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void showInfoMessage(String respuesta) {

    }

    @Override
    public void setRecycler(List<Sprint> sprints) {
        //mySprintRecyclerViewAdapter=new SprintsRecyclerViewAdapter(sprints, mListener);
        recyclerView.setAdapter(mySprintRecyclerViewAdapter);
    }
}
