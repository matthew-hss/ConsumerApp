package com.example.matthew.consumerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText rut = null;
    EditText password = null;
    Button btnIngresar = null;
    Button btnRegistrar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signup(View view){
        Intent intent = new Intent(this, SignupActivity.class);
        rut = (EditText) findViewById(R.id.txtRut);
        password = (EditText) findViewById(R.id.txtPassword);
        String message = "Rut: "+rut.getText().toString()+" \n Password: "+password.getText().toString();
        intent.putExtra("EXTRA", message);
        startActivity(intent);
    }

    public void login(View view){
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);
    }
}
