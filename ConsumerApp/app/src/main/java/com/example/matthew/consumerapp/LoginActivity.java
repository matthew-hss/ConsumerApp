package com.example.matthew.consumerapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.net.CookieHandler;
import java.net.CookieManager;

import utils.WebServiceUtils;

public class LoginActivity extends AppCompatActivity {
    EditText etRut = null;
    EditText etPassword = null;
    String rut = null;
    String password = null;
    int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Manejará las coockies en adelante, se inicializa una vez durante la app
        // ya que al inicializarlo en cada conexión se perderán las cookies anetiormente almacenadas.
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

    public void login(View view) {
        etRut = (EditText) findViewById(R.id.txtRut);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        rut = etRut.getText().toString();
        password = etPassword.getText().toString();

        new Authenticate().execute();
    }


    private class Authenticate
            extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... unused) {
            code = WebServiceUtils.authenticate(rut, password);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            toIndex();
        }
    }

    public void toIndex() {
        if (code == 200) {
            Intent intent = new Intent(this, IndexActivity.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Fallo en autenticación");
            builder.setMessage("Usuario y/o contraseña inválidos!");
            builder.setPositiveButton("Ok", null).show();
        }
    }
}
