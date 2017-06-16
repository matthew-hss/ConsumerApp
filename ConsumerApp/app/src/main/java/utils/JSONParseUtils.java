package utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * Created by matthew on 15-06-17.
 */

public class JSONParseUtils {

    public List<User> findAllUsers() {
        JSONObject serviceResult = WebServiceUtils.requestWebService("https://modena.sportcars.cl/commerce/api/v1/users");

        List<User> foundItems = new ArrayList<>();

        try {
            JSONArray items = serviceResult.getJSONArray("");

            for (int i = 0; i < items.length(); i++) {
                JSONObject obj = items.getJSONObject(i);
                User user = new User();
                user.setRut(obj.getString("rut"));
                user.setFirstName(obj.getString("firstName"));
                user.setLastName(obj.getString("lastName"));
                user.setGender(obj.getString("gender"));
                user.setCommune(obj.getString("commune"));
                user.setEmail(obj.getString("email"));
                user.setPhone(obj.getLong("phone"));
                user.setEkopesos(obj.getLong("ekopesos"));
                user.setCo2(obj.getLong("co2"));
                user.setMaxLimit(obj.getInt("maxLimit"));
                user.setActive(obj.getBoolean("active"));
                foundItems.add(user);
            }

        } catch (JSONException e) {
            // handle exception
            Log.e("Parsing JSON", "Error", e);
        }

        return foundItems;
    }

    public User findUsers() {
        JSONObject serviceResult = WebServiceUtils.requestWebService("https://modena.sportcars.cl/commerce/api/v1/users");

        User foundItem;

        try {
            JSONObject obj = serviceResult.getJSONObject("item");

            foundItem = new User();
            foundItem.setRut(obj.getString("rut"));
            foundItem.setFirstName(obj.getString("firstName"));
            foundItem.setLastName(obj.getString("lastName"));
            foundItem.setGender(obj.getString("gender"));
            foundItem.setCommune(obj.getString("commune"));
            foundItem.setEmail(obj.getString("email"));
            foundItem.setPhone(obj.getLong("phone"));
            foundItem.setEkopesos(obj.getLong("ekopesos"));
            foundItem.setCo2(obj.getLong("co2"));
            foundItem.setMaxLimit(obj.getInt("maxLimit"));
            foundItem.setActive(obj.getBoolean("active"));


        } catch (JSONException e) {
            // handle exception
            foundItem = null;
            Log.e("Parsing JSON", "Error", e);
        }

        return foundItem;
    }

    public List<String> findCountries(){
        List<String> list = new ArrayList<>();

        try {
            JSONObject serviceResult = WebServiceUtils.requestWebService("http://services.groupkt.com/country/get/all");
            String RestResponse = serviceResult.getString("RestResponse");
            JSONObject serviceResult2 = new JSONObject(RestResponse);
            JSONArray items = serviceResult2.getJSONArray("result");

            for (int i = 0; i < items.length(); i++) {
                JSONObject obj = items.getJSONObject(i);
                String country = obj.optString("name");
                list.add(country);
            }

        } catch (JSONException e) {
            Log.e("Parsing JSON", "Error", e);
        }

        return list;

    }
}
