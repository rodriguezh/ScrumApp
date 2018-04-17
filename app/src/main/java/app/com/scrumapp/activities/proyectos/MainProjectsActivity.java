package app.com.scrumapp.activities.proyectos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import app.com.scrumapp.R;
import app.com.scrumapp.adapters.ProjectRecyclerViewAdapter;
import app.com.scrumapp.models.Proyecto;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainProjectsActivity extends AppCompatActivity implements MainProjectsContract.View{

    private MainProjectsContract.Presenter mPresenter;
    private ProjectRecyclerViewAdapter myProjectRecyclerViewAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_projects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPresenter = new MainProjectsPresenter(this);
        recyclerView = (RecyclerView)findViewById(R.id.listProjects);
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
    protected void onPostResume() {
        super.onPostResume();
        mPresenter.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void setPresenter(MainProjectsContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);
    }

    @Override
    public void showInfoMessage(String respuesta) {

    }

    @Override
    public void setRecycler(List<Proyecto> projects) {
        myProjectRecyclerViewAdapter=new ProjectRecyclerViewAdapter(projects);
        recyclerView.setAdapter(myProjectRecyclerViewAdapter);
    }
}
