package app.com.scrumapp.activities.historiausuario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.HashMap;
import java.util.Map;

import app.com.scrumapp.Constants;
import app.com.scrumapp.R;
import app.com.scrumapp.models.HistoriadeUsuario;

import static com.google.common.base.Preconditions.checkNotNull;

public class HistoriaUsuarioActivity extends AppCompatActivity implements HistoriaUsuarioContract.View {
    private RecyclerView huRecyclerView;
    private DatabaseReference mFirebaseDatabaseReference;

    private HistoriaUsuarioContract.Presenter mPresenter;

    private LinearLayoutManager mLinearLayoutManager;
    private FirebaseRecyclerAdapter<HistoriadeUsuario,MessageViewHolder> mFirebaseAdapter;
    EditText edtIdHu,edtDescripcion,edtCriterios;
    TextView txtPrioridad, txtEsfuerzo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         edtIdHu=(EditText)findViewById(R.id.edtIdHU);
         edtDescripcion=(EditText)findViewById(R.id.edtDescripcion);
         edtCriterios=(EditText)findViewById(R.id.edtCriterios);
         txtEsfuerzo=(TextView)findViewById(R.id.tVEsfuerzo);
         txtPrioridad=(TextView)findViewById(R.id.tVPrioridad);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString(Constants.IDHU)!= null){
           Log.e("---->IDHU",bundle.getString(Constants.IDHU));
        }
        mPresenter = new HistoriaUsuarioPresenter(this,bundle.getString(Constants.IDHU));


       // huRecyclerView = (RecyclerView)findViewById(R.id.huRecyclerView);

       // mLinearLayoutManager = new LinearLayoutManager(this);

        //huRecyclerView.setLayoutManager(mLinearLayoutManager);

      /*  mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<HistoriadeUsuario,
                MessageViewHolder>(
                HistoriadeUsuario.class,
                R.layout.item_message,
                MessageViewHolder.class,
                mFirebaseDatabaseReference.child("HistoriasdeUsuario")) {

            @Override
            protected HistoriadeUsuario parseSnapshot(DataSnapshot snapshot) {
                HistoriadeUsuario friendlyMessage = super.parseSnapshot(snapshot);
                if (friendlyMessage != null) {
                    friendlyMessage.set_id(snapshot.getKey());
                }
                return friendlyMessage;
            }

            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder,
                                              final HistoriadeUsuario friendlyMessage, int position) {
               viewHolder.messengerTextView.setText(friendlyMessage.getDescripcion());
                 viewHolder.messageTextView.setText(friendlyMessage.getCriteriosAceptacion());
                //Toast.makeText(getApplicationContext(),"Cambio realizado", Toast.LENGTH_SHORT).show();



            }

        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added MESSAGE.

                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                huRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        huRecyclerView.setLayoutManager(mLinearLayoutManager);
        huRecyclerView.setAdapter(mFirebaseAdapter);*/

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mPresenter.start();
    }

    private void saveHU(String idHU, String descripcion, String criteriosAceptacion){

        String key = mFirebaseDatabaseReference.child("HistoriasdeUsuario").push().getKey();
        HistoriadeUsuario friendlyMessage= new HistoriadeUsuario(idHU,descripcion,criteriosAceptacion);
        Map<String, Object> postValues = friendlyMessage.toMap();

        Map<String, Object> childUpdates = new HashMap<>();

        //childUpdates.put("/chats/" + key, postValues);
        childUpdates.put("/HistoriasdeUsuario/"+key, postValues);

        mFirebaseDatabaseReference.updateChildren(childUpdates);
    }

//postref: mFirebaseDatabaseReference.child("/HistoriasdeUsuario/"+key)
    private void updateHU(DatabaseReference postRef, final String criterio, final String descripcion) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                HistoriadeUsuario p = mutableData.getValue(HistoriadeUsuario.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }
                p.setCriteriosAceptacion(criterio);
                p.setDescripcion(p.getDescripcion()+" "+descripcion);

                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                // Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }

    private void deleteHU(String key){
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/HistoriasdeUsuario/" + key, null);

        mFirebaseDatabaseReference.updateChildren(childUpdates);
    }

    @Override
    public void setPresenter(HistoriaUsuarioContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);
    }

    @Override
    public void loadView(HistoriadeUsuario hu) {
        edtIdHu.setText(hu.getIdHU());
        edtDescripcion.setText(hu.getDescripcion());
        edtCriterios.setText(hu.getCriteriosAceptacion());
        txtEsfuerzo.setText(hu.getPeso()+"");
        txtPrioridad.setText(hu.getPrioridad()+"");
    }

    @Override
    public void showInfoMessage(String respuesta) {

    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView messengerTextView;
        public TextView txtBadge;
        public LinearLayout linearMessage;

        public MessageViewHolder(View v) {
            super(v);
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            messengerTextView = (TextView) itemView.findViewById(R.id.messengerTextView);
            linearMessage=(LinearLayout)itemView.findViewById(R.id.linearMessage);
            txtBadge = (TextView)itemView.findViewById(R.id.badge_notification);
        }
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
              /*  mPresenter.updateUser(edtNombreUsuario.getText().toString(),
                        edtEstudios.getText().toString(),edtPublicaciones.getText().toString(),
                        edtExperiencias.getText().toString());*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
