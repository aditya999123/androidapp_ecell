package app.startups.nitrr.ecell.ecellapp.ContactUs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Random;

import app.startups.nitrr.ecell.ecellapp.R;

/**
 * Created by aditya on 17/5/16.
 */
public class ContactUs extends Activity {
    private RecyclerView recycler1;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter Radapter;
    private ArrayList<datavar> datals;
    Bitmap myBitmap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override

    public void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        datals = new ArrayList<>();

        new AsyncTaskParseJson().execute();
        //datals.add(new datavar(3, "Aditya", "Tech", "7587485272", "aditya999123@gmail.com"));


        recycler1 = (RecyclerView) findViewById(R.id.recycler1);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recycler1.setLayoutManager(lmanager);
        Radapter = new adapter(getApplicationContext(), datals);
        recycler1.setAdapter(Radapter);

         }


    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {


        String yourJsonStringUrl = "http://adityaagr.tk/ContactJson.json";

        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg0) {


            try {

                JsonParser jParser = new JsonParser();
                 datals.add(new datavar(3, "Aditya", "Tech", "7587485272", "aditya999123@gmail.com"));
                String jsonstr = null;
                //String jsonurl = "http://adityaagr.tk/ContactJson";
                jsonstr = jParser.getJSONFromUrl();

               // datals.add(new datavar(3, "Aditya3", "Tech", "7587485272", "aditya999123@gmail.com"));
                //String jsonstr = "{\"Contacts\": [\n {\n\"name\": \"Ram2 Tamada\",\n\"email\": \"ravi@gmail.com\",\n\"designation\" : \"Tech\",\n\"phone\": \"+0000000000\"\n}\n]\n}";
                if (jsonstr != null) {
                    JSONObject jsonobj = new JSONObject(jsonstr);
                 //   datals.add(new datavar(3, "Aditya4", "Tech", "7587485272", "aditya999123@gmail.com"));


                   dataJsonArr = jsonobj.getJSONArray("Contacts");
                    datals.add(new datavar(3, "Aditya", "Tech", "7587485272", "aditya999123@gmail.com"));

                    for (int i = 0; i < dataJsonArr.length(); i++) {
                        JSONObject jsonObject = dataJsonArr.getJSONObject(i);

                        String jsonname = jsonObject.optString("name").toString();
                        String jsondsgn = jsonObject.optString("designation").toString();
                        String jsonnumber = jsonObject.optString("phone").toString();
                        String jsonemail = jsonObject.optString("email").toString();


                        //new ImageLoadTask("http://adityaagr.tk/"+jsonname+".jpg");

                        datals.add(new datavar(i+1, jsonname, jsondsgn, jsonnumber, jsonemail));


                    }

                }
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }



}
