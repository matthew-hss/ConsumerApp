package com.example.matthew.consumerapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

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
    }

    public void login(View view) {
        etRut = (EditText) findViewById(R.id.txtRut);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        rut = etRut.getText().toString();
        password = etPassword.getText().toString();

        ToPost test = new ToPost();
        test.execute();
    }


    private class ToPost
            extends AsyncTask<Void, Void, Void> {
//        private ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
//
//        @Override
//        protected void onPreExecute() {
//            // TODO i18n
//            dialog.setMessage("Por favor espere...");
//            dialog.show();
//        }

        @Override
        protected Void doInBackground(Void... unused) {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("https://modena.sportcars.cl/commerce/login");
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                String body = "username=" + rut + "&password=" + password;
                OutputStream output = new BufferedOutputStream(conn.getOutputStream());
                output.write(body.getBytes());
                output.flush();

                code = conn.getResponseCode();

            } catch (ProtocolException e) {
                Log.e("Protocolo", "Error", e);
            } catch (IOException e) {
                Log.e("IO", "Error", e);
            } finally {
                conn.disconnect();
            }
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
