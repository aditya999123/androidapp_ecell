package app.startups.nitrr.ecell.ecellapp.Bquiz;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import app.startups.nitrr.ecell.ecellapp.R;

public class Sms_Verification extends AppCompatActivity {

    String num1="",name="",lname="",email="",college="",branch="",sem="",otp="";
    int flg=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__verification);
        Button btn=(Button)findViewById(R.id.btn);
        Button btn1=(Button)findViewById(R.id.btn1);
        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        name= getIntent().getExtras().getString("name").toString();
                        EditText num=(EditText)findViewById(R.id.num);
                        num1=num.getText().toString();
                        new GetData().execute();
                    }
                });
        btn1.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        name= getIntent().getExtras().getString("name").toString();
                        lname= getIntent().getExtras().getString("lname").toString();
                        email= getIntent().getExtras().getString("email").toString();
                        college= getIntent().getExtras().getString("college").toString();
                        branch= getIntent().getExtras().getString("branch").toString();
                        sem= getIntent().getExtras().getString("sem").toString();
                        EditText num=(EditText)findViewById(R.id.num);
                        EditText otp1=(EditText)findViewById(R.id.verify);
                        otp=otp1.getText().toString();
                        num1=num.getText().toString();
                        new PutData().execute();
                    }
                });





    }

    private class PutData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pDialog = new ProgressDialog(Sms_Verification.this);
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
            Log.d("Response","Beore sh"+name);
            jsonParser sh = new jsonParser();



            String url="";
            url="http://192.168.0.110:8000/ver_otp/"+name+"/"+lname+"/"+email+"/"+"college"+"/"+branch+"/"+sem+"/"+num1+"/"+otp;

            String jsonStr = sh.getJSONFromUrl(url);
            Log.d("Response", "> " + jsonStr);
            try
            {
                JSONObject jsonRootObject = new JSONObject(jsonStr);
                String s=jsonRootObject.optString("status").toString();
                Log.d("Response",s);

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
    private class GetData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pDialog = new ProgressDialog(Sms_Verification.this);
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
            Log.d("Response","Beore sh"+name);
            jsonParser sh = new jsonParser();



            String url="";
             url="http://192.168.0.110:8000/get_otp/"+name+"/"+num1;
            String jsonStr = sh.getJSONFromUrl(url);
            Log.d("Response", "> " + jsonStr);
            try
            {
                JSONObject jsonRootObject = new JSONObject(jsonStr);
                String s=jsonRootObject.optString("success").toString();
                Log.d("Response",s);

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
