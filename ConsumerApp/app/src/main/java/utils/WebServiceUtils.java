package utils;

import android.os.Build;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
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
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            // manejo de problemas
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // manejo de autorización (en caso de que el servicio requiera login de usuario)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // manejo de otros errores como 404, 500,...
            }

            // Crea un objeto JSONTokener desde el contenido
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return new JSONTokener(getResponseText(in)).nextValue();
//

        } catch (MalformedURLException e) {
            Log.e("Invalid URL", "Error", e);
        } catch (SocketTimeoutException e) {
            Log.e("Time out", "Error", e);
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
            Log.e("InputStream", "Error", e);
        } catch (JSONException e) {
            Log.e("Body not valid JSON", "Error", e);
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
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

    public static int authenticate(String rut, String password) {
        HttpURLConnection urlConnection = null;
        int code = 404;
        try {
            URL urlToRequest = new URL("https://modena.sportcars.cl/commerce/login");
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            String body = "username=" + rut + "&password=" + password;
            OutputStream output = new BufferedOutputStream(urlConnection.getOutputStream());
            output.write(body.getBytes());
            output.flush();

            code = urlConnection.getResponseCode();

        } catch (ProtocolException e) {
            Log.e("Protocolo", "Error", e);
        } catch (IOException e) {
            Log.e("IO", "Error", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return code;
    }
}
