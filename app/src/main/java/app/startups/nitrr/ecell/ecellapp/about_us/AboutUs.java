package app.startups.nitrr.ecell.ecellapp.about_us;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import app.startups.nitrr.ecell.ecellapp.R;


public class AboutUs extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);
        TextView tv=(TextView)findViewById(R.id.textView);
        tv.getBackground().setAlpha(40);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("About Us");

    }
}
