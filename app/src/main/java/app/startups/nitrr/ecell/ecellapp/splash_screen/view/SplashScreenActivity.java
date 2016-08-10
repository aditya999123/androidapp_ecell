package app.startups.nitrr.ecell.ecellapp.splash_screen.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.MyApplication;
import app.startups.nitrr.ecell.ecellapp.helper.SharedPrefs;
import app.startups.nitrr.ecell.ecellapp.home.view.Home;
import app.startups.nitrr.ecell.ecellapp.splash_screen.model.RetrofitSplashScreenProvider;
import app.startups.nitrr.ecell.ecellapp.splash_screen.presenter.SplashScreenPresenter;
import app.startups.nitrr.ecell.ecellapp.splash_screen.presenter.SplashScreenPresenterImpl;
import app.startups.nitrr.ecell.ecellapp.welcome.view.WelcomeActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);
        sharedPrefs = new SharedPrefs(this);
        SplashScreenPresenter splashScreenPresenter = new SplashScreenPresenterImpl(this, new RetrofitSplashScreenProvider());
        splashScreenPresenter.insertFcm(MyApplication.fcm_token);

        /*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

             }
        }, 4000);

        */
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(SplashScreenActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fcmInsertStatus(boolean successful) {

        if(successful){
            sharedPrefs.setFCM(MyApplication.fcm_token);
            Intent in=new Intent(SplashScreenActivity.this,WelcomeActivity.class);
            startActivity(in);

        }
        if (sharedPrefs.isLoggedIn()) {
            Intent in = new Intent(SplashScreenActivity.this, Home.class);
            startActivity(in);
            finish();
        } else {
            Intent signIn = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
            startActivity(signIn);
            finish();

        }

    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }
}
