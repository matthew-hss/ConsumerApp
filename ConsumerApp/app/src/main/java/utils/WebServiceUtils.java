package utils;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by matthew on 15-06-17.
 */

public class WebServiceUtils {
    public static Object requestWebService(String serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // Crea la conexión
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();
//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            // manejo de problemas
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // manejo de autorización (en caso de que el servicio requiera login de usuario)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // manejo de otros errores como 404, 500,...
            }

            // Crea un objeto JSON desde el contenido
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            return new JSONObject(getResponseText(in));

            return new JSONTokener(getResponseText(in)).nextValue();
//

        } catch (MalformedURLException e) {
            // URL is invalid
            Log.e("Invalid URL", "Error", e);
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
            Log.e("Time out", "Error", e);
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
            Log.e("InputStream", "Error", e);
        } catch (JSONException e) {
            // response body is no valid JSON string
            Log.e("Body not JSON", "Error", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

    public static int authenticate(String rut, String password) {
        HttpURLConnection conn = null;
        int code = 404;
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
        return code;
    }
}
