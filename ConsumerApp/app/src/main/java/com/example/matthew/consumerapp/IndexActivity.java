package com.example.matthew.consumerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import utils.JSONParseUtils;

public class IndexActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
                    toHome();
                    return true;
//                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    readQR();
//                    return true;
                case R.id.navigation_reader:
                    toReadQR();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        new RequestItemsServiceTask().execute();
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy =
//                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//
//        ListView lv = (ListView) findViewById(R.id.lvLista);
//
//        List<String> countries = new JSONParseUtils().findCountries();
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
//
//        lv.setAdapter(arrayAdapter);
    }

    public void toReadQR() {
        Intent intent = new Intent(this, CamActivity.class);
        startActivity(intent);
    }

    public void toHome() {
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);
    }

    private class RequestItemsServiceTask
            extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog =
                new ProgressDialog(IndexActivity.this);
        private List<String> itemsList;

        @Override
        protected void onPreExecute() {
            // TODO i18n
            dialog.setMessage("Por favor espere...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... unused) {
            JSONParseUtils itemService = new JSONParseUtils();
            try {
                itemsList = itemService.findCountries();
            } catch (Throwable e) {
                // handle exceptions
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {

            // setListAdapter must not be called at doInBackground()
            // since it would be executed in separate Thread
            ListView lv = (ListView) findViewById(R.id.lvLista);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(IndexActivity.this, android.R.layout.simple_list_item_1, itemsList);

            lv.setAdapter(arrayAdapter);


            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
