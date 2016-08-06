package app.startups.nitrr.ecell.ecellapp.LogIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import app.startups.nitrr.ecell.ecellapp.R;

public class Bquiz_Intro extends AppCompatActivity {
String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ResponseOtp","1st");
        setContentView(R.layout.activity_bquiz__intro);
        Button btn5=(Button)findViewById(R.id.playquiz);

        url=getIntent().getExtras().getString("url").toString();

        btn5.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        new GetData().execute();

                    }

                }

        );
    }
    private class GetData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pDialog = new ProgressDialog(Bquiz_Intro.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
            Log.d("ResponseOtp","4th");
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url2=url+"/get_ques";
            jsonParser sh=new jsonParser();
            Log.d("ResponseOtp","5th");
            String jsonStr = sh.getJSONFromUrl(url2);
            Log.d("ResponseOtp", "> " + jsonStr);
            try
            {
                JSONObject jsonRootObject = new JSONObject(jsonStr);
                int time=Integer.parseInt(jsonRootObject.optString("time").toString());
                Log.d("ResponseOtp", "> " + time+"\n");
                int question_no=Integer.parseInt(jsonRootObject.optString("question_no").toString());
                Log.d("ResponseOtp", "> " +question_no+"\n");
                String question=jsonRootObject.optString("question").toString();
                String option1=jsonRootObject.optString("option1").toString();
                Log.d("ResponseOtp", "> " + question+"\n");
                String option2=jsonRootObject.optString("option2").toString();
                String option3=jsonRootObject.optString("option3").toString();
                String option4=jsonRootObject.optString("option4").toString();
                String message=jsonRootObject.optString("message").toString();
                if(time<0)
                {
                    Log.d("ResponseOtp",""+time+"\n"+question_no+"\n"+question+"\n"+option1+"\n"+option2+"\n"+option3+"\n"+option4+"\n"+message);
                }
                else if(time>0)
                {
                    Intent in=new Intent(Bquiz_Intro.this,BQuizQuestion.class);
                    Log.d("ResponseOtp", ">");
                    in.putExtra("time",time);
                    in.putExtra("Message",message);
                    startActivity(in);
                    Log.d("ResponseOtp",""+time+"\n"+question_no+"\n"+question+"\n"+option1+"\n"+option2+"\n"+option3+"\n"+option4+"\n"+message);
                }
                else if(time==0)
                {
                    Log.d("ResponseOtp",""+time+"\n"+question_no+"\n"+question+"\n"+option1+"\n"+option2+"\n"+option3+"\n"+option4+"\n"+message);
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
