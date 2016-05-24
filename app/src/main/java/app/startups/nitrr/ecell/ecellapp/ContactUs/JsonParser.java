package app.startups.nitrr.ecell.ecellapp.ContactUs;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonParser {
 //   InputStream in = null;
    //BufferedReader in=null;
//String line,x=null;
    String x=null;
    public String getJSONFromUrl() {
        //make HTTP request

        HttpURLConnection urlConnection = null;
String ans=null;
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL("http://adityaagr.tk/ContactJson");

                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line+"\n");
                }

            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }


            return result.toString();
        }







    }
