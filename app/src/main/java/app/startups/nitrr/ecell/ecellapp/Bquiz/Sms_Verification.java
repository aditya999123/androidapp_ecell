package app.startups.nitrr.ecell.ecellapp.Bquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import app.startups.nitrr.ecell.ecellapp.Bquiz.model.Json_Parser;
import app.startups.nitrr.ecell.ecellapp.R;

public class Sms_Verification extends AppCompatActivity {

    String num1="",name="",lname="",email="",college="",branch="",sem="",otp="",token="",url="";
    int flg=1;
    Json_Parser sh=new Json_Parser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__verification);
        Button btn=(Button)findViewById(R.id.btn);
        final Button btn1=(Button)findViewById(R.id.btn1);
        EditText url1=(EditText)findViewById(R.id.url);
        url=url1.getText().toString();
        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        name= getIntent().getExtras().getString("name").toString();
                        EditText num=(EditText)findViewById(R.id.num);
                        num1=num.getText().toString();
                        if(num1.length()!=10)
                        {
                            Toast.makeText(Sms_Verification.this, "ENTER CORRECT MOBILE NUMBER!",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        new GetData().execute();
                    }
                });
      /*  SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                otp=messageText.substring(messageText.length()-4,messageText.length());
                Log.d("ResponseOtp",messageText);
                Log.d("ResponseOtp",otp);
                Toast.makeText(Sms_Verification.this,"Otp- "+otp , Toast.LENGTH_LONG).show();
                btn1.performClick();
            }
        });
        */
        btn1.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        name= getIntent().getExtras().getString("name").toString();
                        lname= getIntent().getExtras().getString("lname").toString();
                        email= getIntent().getExtras().getString("email").toString();
                        token=getIntent().getExtras().getString("token").toString();
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
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("ResponseOtp",url);

           String  url2=url+"/ver_otp/"+name+"/"+lname+"/"+email+"/"+"college"+"/"+sem+"/"+branch+"/"+num1+"/"+otp+"/"+token;
            Log.d("ResponseOtp",url);
            String jsonStr = sh.getJSONFromUrl(url2);
            Log.d("ResponseOtp", "> " + jsonStr);
            try
            {
                JSONObject jsonRootObject = new JSONObject(jsonStr);
                String s=jsonRootObject.optString("status").toString();
                Log.d("ResponseOtp",s);

                if(s.equals("verified"))
                {
                    Intent in=new Intent(Sms_Verification.this,Bquiz_Intro.class);
                    Log.d("ResponseOtp", ">");
                    in.putExtra("url",url);
                    startActivity(in);

                }
                else {
                    Toast.makeText(Sms_Verification.this, "ENTER CORRECT OTP",
                            Toast.LENGTH_LONG).show();
                    Intent in=new Intent(Sms_Verification.this,Sms_Verification.class);

                    startActivity(in);
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
    private class GetData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pDialog = new ProgressDialog(Sms_Verification.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String url2=url+"/get_otp/"+name+"/"+num1;
            Log.d("ResponseOtp","Beore sh"+url);
            String jsonStr = sh.getJSONFromUrl(url2);
            Log.d("ResponseOtp", "> " + jsonStr);
            try
            {
                JSONObject jsonRootObject = new JSONObject(jsonStr);
                String s=jsonRootObject.optString("success").toString();
                Log.d("ResponseOtp",s);
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
            EditText verify=(EditText)findViewById(R.id.verify);
            verify.setVisibility(View.VISIBLE);
            Button btn1=(Button)findViewById(R.id.btn1);
            btn1.setVisibility(View.VISIBLE);
            Button btn=(Button)findViewById(R.id.btn);
            btn.setText("Resend Otp");
        }

    }
}