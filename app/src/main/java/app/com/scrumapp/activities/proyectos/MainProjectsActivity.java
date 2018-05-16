package app.com.scrumapp.activities.proyectos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.activities.MainActivity;
import app.com.scrumapp.activities.Sprints.MainSprintFragment;
import app.com.scrumapp.activities.login.LoginActivity;
import app.com.scrumapp.data.remote.retrofit.APIServiceLogin;
import app.com.scrumapp.data.remote.retrofit.ApiUtils;
import app.com.scrumapp.fragments.huasignada.HUAsignadasFragment;
import app.com.scrumapp.models.Proyecto;
import app.com.scrumapp.models.Sprint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainProjectsActivity extends AppCompatActivity implements ProjectsFragment.OnListFragmentInteractionListener, MainSprintFragment.OnFragmentInteractionListener {
    private SharedPreferences prefs;
    private String rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_proj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Fragment fragment= new ProjectsFragment();
        loadFragment(fragment,"Proyectos");
        prefs = getSharedPreferences("Preferences",MODE_PRIVATE);
        rol=prefs.getString("rol", "");
    }

    private void loadFragment(Fragment fragment, String tag) {
        // load fragment
        if (fragment != null) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_containerClass);
            if (currentFragment == null || !currentFragment.getClass().getName().equalsIgnoreCase(fragment.getClass().getName())) {
                //carga del primer fragment justo en la carga inicial de la app
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_containerClass, fragment,tag)
                        .commit();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
            }
        }
    }

    @Override
    public void onListFragmentInteraction(Proyecto item) {
        Bundle args = new Bundle();
        args.putInt(Constants.IDPROYECTO,item.getId());
        Fragment fragment = new MainSprintFragment();
        fragment.setArguments(args);
        loadFragment(fragment,"Sprints");
    }

    @Override
    public void onBackPressed() {

        Fragment fragment= new ProjectsFragment();
        loadFragment(fragment,"Proyectos");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                APIServiceLogin apiServicelogin = ApiUtils.getAPIServiceLogin(Constants.BASEURLLOGIN);

                String us=prefs.getString("usuario", "");
                final Context context=this;
                apiServicelogin.loginOff(us,"off").enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        LinkedTreeMap<String,String> linkedTreeMap = (LinkedTreeMap<String, String>) response.body();
                        if (linkedTreeMap.containsKey("sesion")){
                            prefs =getSharedPreferences("Preferences", MODE_PRIVATE);
                            prefs.edit().clear().apply();

                            Intent intent = new Intent(context,LoginActivity.class);
                            //borrar  el historial apenas salga de la sesión por seguridad
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Sprint sprint) {
        if(!rol.equals("Developer")){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra(Constants.IDSPRINT,sprint.getId_sprint());
            intent.putExtra(Constants.IDPROYECTO,sprint.getId_pb());
            intent.putExtra(Constants.PESO,sprint.getPeso_total_pb());
            intent.putExtra(Constants.DIAS,sprint.getDias_sprint());
            startActivity(intent);
        }else{
            Fragment fragment = new HUAsignadasFragment();
            Bundle bundle= new Bundle();
            bundle.putInt(Constants.IDPROYECTO,sprint.getId_pb());
            bundle.putInt(Constants.IDSPRINT,sprint.getId_sprint());
            fragment.setArguments(bundle);
            loadFragment(fragment,"HUAsignadas");
        }
    }
}
