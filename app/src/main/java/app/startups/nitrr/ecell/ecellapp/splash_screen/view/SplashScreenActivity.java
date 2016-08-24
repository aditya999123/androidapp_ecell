package app.startups.nitrr.ecell.ecellapp.splash_screen.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import app.startups.nitrr.ecell.ecellapp.BuildConfig;
import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.MyApplication;
import app.startups.nitrr.ecell.ecellapp.helper.SharedPrefs;
import app.startups.nitrr.ecell.ecellapp.home.view.Home;
import app.startups.nitrr.ecell.ecellapp.splash_screen.model.RetrofitSplashScreenProvider;
import app.startups.nitrr.ecell.ecellapp.splash_screen.model.data.SplashScreenData;
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

        if (sharedPrefs.getFcm() == null) {
        SplashScreenPresenter splashScreenPresenter = new SplashScreenPresenterImpl(this, new RetrofitSplashScreenProvider());
        splashScreenPresenter.insertFcm(MyApplication.fcm_token);
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
        /*Handler handler = new Handler();
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
    public void fcmInsertStatus(SplashScreenData splashScreenData) {

        int i = splashScreenData.getVersion();

        if (i > BuildConfig.VERSION_CODE) {
            final Dialog dialog = new Dialog(SplashScreenActivity.this);
            dialog.setContentView(R.layout.activity_rules__dialog_box);
            Button btn = (Button) dialog.findViewById(R.id.dialog_button);
            TextView rules = (TextView) dialog.findViewById(R.id.rules5);
            if (splashScreenData.getCompulsory_update() == 1) {
                rules.setText("We've found some major improvements in our app . To enjoy ECellApp Please Update it");
                dialog.setCancelable(false);

            } else {
                rules.setText("Please Update the app for Better experience");

            }

            dialog.setTitle("App Update");
            btn.setText("Update");
            dialog.show();
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            });
        } else if (splashScreenData.isSuccess()) {
            sharedPrefs.setFCM(MyApplication.fcm_token);
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

    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
