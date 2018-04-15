package app.com.scrumapp.activities.historiausuario;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.models.HistoriadeUsuario;
import app.com.scrumapp.models.Usuario;
import app.com.scrumapp.utils.Util;
import android.text.TextUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class HistoriaUsuarioActivity extends AppCompatActivity implements HistoriaUsuarioContract.View {

    private HistoriaUsuarioContract.Presenter mPresenter;
    EditText edtDescripcion,edtCriteriosAceptacion,edtinfAdicional,edtMotivoCandelacion;
    Chronometer chronometer;
    Button btnInciar,btnDetener,btnFinalizarTarea,btnCancelarTarea;
    TextView txtNoHU, txtProyecto,txtSprint,txtEstado,txtEsfuerzo,txtPrioridad,txtFechaInicio,txtFechaFin;
    private Spinner spinnerUsers;
    private String keyHistoriaUsuario;
    private Usuario usuario;
    private String fechaInicial;
    private int tipoForm;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       txtNoHU=(TextView)findViewById(R.id.txtNoHU);
       txtProyecto=(TextView)findViewById(R.id.txtProyecto);
       txtSprint=(TextView)findViewById(R.id.txtSprint);
       txtEstado=(TextView)findViewById(R.id.txtEstado);
       txtEsfuerzo=(TextView)findViewById(R.id.txtEsfuerzo);
       txtPrioridad=(TextView)findViewById(R.id.txtPrioridad);
       txtFechaInicio=(TextView)findViewById(R.id.txtFechaInicio);
       txtFechaFin=(TextView)findViewById(R.id.txtFechaFin);
       edtDescripcion=(EditText)findViewById(R.id.edtDescripcion);
       edtCriteriosAceptacion=(EditText)findViewById(R.id.edtCriteriosAceptacion);
       edtinfAdicional=(EditText)findViewById(R.id.edtinfAdicional);
       edtMotivoCandelacion=(EditText)findViewById(R.id.edtMotivoCandelacion);
       btnInciar=(Button)findViewById(R.id.btnInciar);
       btnDetener=(Button)findViewById(R.id.btnDetener);
       btnFinalizarTarea=(Button)findViewById(R.id.btnFinalizarTarea);
       btnCancelarTarea=(Button)findViewById(R.id.btnCancelarTarea);
       chronometer=(Chronometer)findViewById(R.id.chronometer);
       spinnerUsers = (Spinner) findViewById(R.id.spinnerUsuario);
       linearLayout=(LinearLayout)findViewById(R.id.linearControl);
       btnInciar.setOnClickListener(mStartListener);
       btnDetener.setOnClickListener(mStopListener);
       btnCancelarTarea.setOnClickListener(mCancelarHU);
       btnFinalizarTarea.setOnClickListener(mFinalizarHU);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);

       Bundle bundle = getIntent().getExtras();

        if(bundle.getString(Constants.IDHU)!= null){
            mPresenter = new HistoriaUsuarioPresenter(this,bundle.getString(Constants.IDHU));
        }

        if (bundle.getInt(Constants.FORMTYPE)==Constants.FORMASSIGN){
            tipoForm=Constants.FORMASSIGN;
        }else{
            tipoForm=Constants.FORMUPDATE;
            spinnerUsers.setEnabled(false);
            edtCriteriosAceptacion.setFocusable(false);
            edtDescripcion.setFocusable(false);
            linearLayout.setVisibility(View.VISIBLE);
        }


    }

    public void msnCancelacion(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener mStartListener = new View.OnClickListener() {
        public void onClick(View v) {
           mPresenter.saveUserHistory( actualizarHU(Constants.DOING));
            chronometer.start();
        }
    };

    View.OnClickListener mStopListener = new View.OnClickListener() {
        public void onClick(View v) {
            mPresenter.saveUserHistory(actualizarHU(Constants.STOP));
            chronometer.stop();
        }
    };


    View.OnClickListener mCancelarHU = new View.OnClickListener() {
        public void onClick(View v) {
            String motivo = edtMotivoCandelacion.getText().toString();

            if(!TextUtils.isEmpty(motivo)){
                mPresenter.saveUserHistory(actualizarHU(Constants.CANCEL));
                chronometer.stop();
            }else {
                msnCancelacion("Debe ingresar motivo de cancelación");
                return;
            }
        }
    };

    View.OnClickListener mFinalizarHU = new View.OnClickListener() {
        public void onClick(View v) {
            mPresenter.saveUserHistory(actualizarHU(Constants.DONE));
            chronometer.stop();

        }
    };

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mPresenter.start();
    }


    @Override
    public void setPresenter(HistoriaUsuarioContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);
    }

    @Override
    public void loadView(HistoriadeUsuario hu) {
        keyHistoriaUsuario=hu.getId();
        txtNoHU.setText(hu.getId_hu()+"");
        txtProyecto.setText(hu.getId_proyecto()+"");
        txtSprint.setText(hu.getId_sprint()+"");
        txtEstado.setText(hu.getEstado());
        txtEsfuerzo.setText("Esfuerzo: "+hu.getEsfuerzo());
        txtPrioridad.setText("Prioridad: "+hu.getPrioridad());
        txtFechaInicio.setText(hu.getFechaInicio());
        txtFechaFin.setText(hu.getFechaFin());
        edtDescripcion.setText(hu.getDescripcion());
        edtCriteriosAceptacion.setText(hu.getCriterio_aceptacion());
        edtinfAdicional.setText(hu.getInformacionadicional());
        edtMotivoCandelacion.setText(hu.getMotivocancelacion());
        usuario=hu.getDesarrollador();
        fechaInicial=hu.getFechaInicio();
    }

    @Override
    public void showInfoMessage(String respuesta) {
        Toast.makeText(this,respuesta,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setSpinnerUser(ArrayList<Usuario> usuarios) {
        ArrayAdapter<Usuario> adapter= new ArrayAdapter<Usuario>(this,R.layout.support_simple_spinner_dropdown_item, usuarios);
        spinnerUsers.setAdapter(adapter);
    }

    @Override
    public void setTimeChronometer(int min, int seg) {
        chronometer.setBase(SystemClock.elapsedRealtime() - (min * 60000 + seg * 1000));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusave, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_save:
                if(tipoForm==Constants.FORMASSIGN){
                    mPresenter.saveUserHistory(asignar());
                }else{
                    mPresenter.saveUserHistory(actualizarHU(""));
                }
               break;
        }
        return super.onOptionsItemSelected(item);
    }

    private HistoriadeUsuario asignar(){
        HistoriadeUsuario hu = new HistoriadeUsuario();
        hu.setId(keyHistoriaUsuario);
        hu.setEstado(Constants.TODO);
        hu.setAsignada(true);
        hu.setDesarrollador((Usuario)spinnerUsers.getSelectedItem());
        return hu;
    }

    private HistoriadeUsuario actualizarHU(String estado){

        HistoriadeUsuario hu = new HistoriadeUsuario();
        hu.setId(keyHistoriaUsuario);
        hu.setInformacionadicional(edtinfAdicional.getText().toString());
        hu.setTiempoTranscurrido(chronometer.getText().toString());
        hu.setDesarrollador((Usuario)spinnerUsers.getSelectedItem());
        if(fechaInicial==null){
            hu.setFechaInicio(Util.formatFechaActual());
        }else{
            hu.setFechaInicio(txtFechaInicio.getText().toString());
        }
        hu.setAsignada(true);
       if(estado.length()>0){ 
           hu.setEstado(estado);
       }
        if(estado.equals(Constants.DONE) || estado.equals(Constants.CANCEL)){
            hu.setFechaFin(Util.formatFechaActual());
            hu.setMotivocancelacion(edtMotivoCandelacion.getText().toString());
        }
        return hu;
    }

}
