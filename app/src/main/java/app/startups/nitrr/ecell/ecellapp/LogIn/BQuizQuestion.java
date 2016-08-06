package app.startups.nitrr.ecell.ecellapp.LogIn;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import app.startups.nitrr.ecell.ecellapp.R;

public class BQuizQuestion extends AppCompatActivity {
        TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bquiz_question);
         timer=(TextView)findViewById(R.id.timer1);
        int time=getIntent().getExtras().getInt("time");

        time*=1000;
        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                    timer.setText("BQuiz will start shortly in \n" + millisUntilFinished / 60000 + "minutes"+ (millisUntilFinished/1000)%60 +"seconds");

            }
            public void onFinish() {
                timer.setText("done!");
            }
        }.start();

    }
   /* class Timer extends Thread
    {
        public void run()
        {
            for(int i=0;i<100;i++)
            {

            }
        }
    }
    */
}
