package com.example.matthew.consumerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import model.User;
import utils.JSONParseUtils;
import utils.PreferenceUtils;

public class IndexActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toHome();
                    return true;
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.preferences:
            {
                Intent intent = new Intent(this, PreferenceActivity.class);
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
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
        private ProgressDialog dialog = new ProgressDialog(IndexActivity.this);
        private List<String> itemsList = new ArrayList<>();
        private List<User> userList;

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
                userList = itemService.findAllUsers();
                if (!userList.isEmpty()) {
                    for (User user : userList) {
                        itemsList.add(user.getFirstName() + " " + user.getLastName() + " " + user.getRut());
                    }
                    itemsList.add(PreferenceUtils.getPref("companyRut"));
                }
            } catch (Exception e) {
                Log.e("Getting users", "Error", e);
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