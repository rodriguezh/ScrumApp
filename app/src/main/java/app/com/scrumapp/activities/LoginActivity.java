package app.com.scrumapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import app.com.scrumapp.R;
import app.com.scrumapp.utils.Util;

public class LoginActivity extends AppCompatActivity {
    //prueba

    //lo siguiente permite crear las credenciales del usuario, guardar el usuario y contraseña si le da recordar
    private SharedPreferences prefs;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();

        //se crea un nombre y el contexto será privado, puede compartir también con otras aplicaciones si se le cambia el MODE
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        setCredentialsIfExists();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (login(email,password)){
                    goToMain();
                    saveOnPreferences(email,password);
                }
            }
        });
    }

    private void setCredentialsIfExists() {
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            editTextEmail.setText(email);
            editTextPassword.setText(password);
        }
    }

    private void bindUI(){
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        btnLogin = (Button) findViewById(R.id.buttonLogin);

    }

    private boolean login (String email, String password){
        if(!isValidEmail(email)){
            Toast.makeText(this,"Email is not valid, please try again",Toast.LENGTH_LONG).show();
            return false;
        }else if(!isValidPassword(password)){
            Toast.makeText(this,"Password is not valid,4 characters or more, please try again",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void saveOnPreferences(String email, String password){
        if(switchRemember.isChecked()){
            // permite escribir en el editor
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("pass",password);
            // para que la instrucción sea sincrona se coloca editr.commit() espera que termine todo lo anterior para continuar con la siguiente línea
            editor.apply();
        }
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        return password.length() >= 4;
    }

    private void goToMain(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        //la siguiente instrucción permite que se cierre la sesión y la aplicación si se devuelve a la pantalla de login
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
