package com.example.matthew.consumerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import model.Commune;
import model.Province;
import model.Region;
import model.User;

public class SignupActivity extends AppCompatActivity {
    EditText etRut = null;
    EditText etPassword = null;
    EditText etPasswordConfirm = null;
    EditText etUserName = null;
    EditText etFirstName = null;
    EditText etLastName = null;
    EditText etGender = null;
    EditText etCommune = null;
    EditText etEmail = null;
    EditText etPhone = null;
    Button btnSignup = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//      Intent intent = getIntent();
//       String message = intent.getStringExtra("EXTRA");
//        EditText et = (EditText) findViewById(R.id.txtMessage);
//        et.setText(message);

//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(message);
//
//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
//        layout.addView(textView);
    }

    public void signup(View view) {
        etRut = (EditText) findViewById(R.id.txtRut);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.txtPasswordConfirm);
        etUserName = (EditText) findViewById(R.id.txtUserName);
        etFirstName = (EditText) findViewById(R.id.txtFirstName);
        etLastName = (EditText) findViewById(R.id.txtLastName);
        etGender = (EditText) findViewById(R.id.txtGender);
        etCommune = (EditText) findViewById(R.id.txtCommune);
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPhone = (EditText) findViewById(R.id.txtPhone);

        User user = new User();
        Commune commune = new Commune();
        Province province = new Province();
        Region region = new Region();

        if(etPassword.getText().toString().equalsIgnoreCase(etPasswordConfirm.getText().toString())){
//            user.setRut(Integer.parseInt(etRut.getText().toString()));
//            user.setPassword(etPassword.getText().toString());
//            user.setUsername(etUserName.getText().toString());
//            user.setFirstName(etFirstName.getText().toString());
//            user.setLastName(etLastName.getText().toString());
        }



        //if(registrar)
    }
}
