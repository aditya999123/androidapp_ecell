package app.startups.nitrr.ecell.ecellapp.LogIn;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Iket on 7/31/2016.
 */
public class jsonParser {
    String x = null;

    public String getJSONFromUrl(String url1)

    {


        HttpURLConnection urlConnection = null;
        String ans = null;
        StringBuilder result = new StringBuilder();

        try {

            URL url = new URL(url1);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(1000);

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        urlConnection.disconnect();
        return result.toString();
    }
}
