package app.startups.nitrr.ecell.ecellapp.events_json.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;

/**
 * Created by Iket on 7/29/2016.
 */

public class EventPage extends AppCompatActivity {
    String jsonStr;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<EventsData> data=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_of_events);
        new GetData().execute();
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        mAdapter=new Adapter(EventPage.this,data);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(EventPage.this));


    }

    private class GetData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pDialog = new ProgressDialog(EventPage.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {


            jsonParser sh = new jsonParser();
            jsonStr = sh.getJSONFromUrl();

            Log.d("Response: ", "> " + jsonStr);

            try
            {
                JSONObject jsonRootObject = new JSONObject(jsonStr);
                JSONArray jsonArray = jsonRootObject.optJSONArray("events");
                for(int i=0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    EventsData eventsData=new EventsData();
                    eventsData.eventName=jsonObject.optString("name").toString();
                    eventsData.date=jsonObject.optString("date").toString();
                    eventsData.venue=jsonObject.optString("venue").toString();
                    eventsData.description=jsonObject.optString("description").toString();
                    data.add(eventsData);


                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            return null;


        }
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();



        }

    }



}
