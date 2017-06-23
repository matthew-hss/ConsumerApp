package utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.matthew.consumerapp.MyApp;

/**
 * Created by matthew on 23-06-17.
 */

public class PreferenceUtils {


    public static void putPref(String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApp.getContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPref(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApp.getContext());
        return preferences.getString(key, "");
    }
}
