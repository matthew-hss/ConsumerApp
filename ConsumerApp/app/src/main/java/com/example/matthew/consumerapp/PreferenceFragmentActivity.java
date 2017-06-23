package com.example.matthew.consumerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import java.util.regex.Pattern;

import utils.RutUtils;

public class PreferenceFragmentActivity extends PreferenceFragment {

    private EditTextPreference companyRut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_preference_fragment);
        addPreferencesFromResource(R.xml.preferences);

        companyRut = (EditTextPreference) getPreferenceScreen().findPreference("companyRut");

        companyRut.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean flag = true;
                if (!RutUtils.isRut(newValue.toString())) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Rut no válido");
                    builder.setMessage("El rut ingresado no es válido");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.show();
                    flag = false;
                } else {
                    //Esta bloque "ELSE" no está dando formato al valor a guardar!! hay que revisarlo!!
                    int rut = RutUtils.parseRut(newValue.toString());
                    String fullRut = RutUtils.formatRut(rut);
                    newValue = (Object) fullRut;
                    flag = true;
                }
                return flag;
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context hostActivity = getActivity();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(hostActivity);
    }
}
