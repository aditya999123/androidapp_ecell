package app.startups.nitrr.ecell.ecellapp.about_us.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.GlideImageLoader;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.ImageLoader;

/**
 * Created by Iket on 8/20/2016.
 */

public class AboutUsPage extends AppCompatActivity implements AboutUsInterface{
    ProgressBar progressBar;
    TextView textView;
    ImageView imageView;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("About Us");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void setData(AboutUsData aboutUsData)
    {
        imageView=(ImageView) findViewById(R.id.about_img);
        textView=(TextView) findViewById(R.id.about_us);
        textView.setText(""+aboutUsData.getDescription());
        textView.getBackground().setAlpha(20);
        imageLoader=new GlideImageLoader(this);
        imageLoader.loadImage(aboutUsData.getImage().toString(),imageView);
    }

    @Override
    public void showLoading(boolean show) {
        progressBar=(ProgressBar) findViewById(R.id.progress_aboutus);
        if(show)
        progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(String message) {

    }


}
