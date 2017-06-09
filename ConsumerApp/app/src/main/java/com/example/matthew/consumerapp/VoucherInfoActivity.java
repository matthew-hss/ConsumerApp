package com.example.matthew.consumerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class VoucherInfoActivity extends AppCompatActivity {

    IndexActivity ia = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_reader:
                    toReadQR();
                    //return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_info);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        String qrInfo = intent.getStringExtra("EXTRA");
        EditText etQR = (EditText) findViewById(R.id.txtUsedDate);
        etQR.setText(qrInfo);
    }

    public void toReadQR() {
        Intent intent = new Intent(this, CamActivity.class);
        startActivity(intent);
    }

    public void consumeQr(View view){
        new AlertDialog.Builder(this)
                .setMessage("¿Desea consumir su código QR?")
                .setCancelable(false)
                .setNegativeButton("No", null)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //VoucherInfoActivity.this.finish();
                        EditText etQR = (EditText) findViewById(R.id.txtUsedDate);
                        etQR.setText("consumido!");
                    }
                })
                .show();
    }
}
