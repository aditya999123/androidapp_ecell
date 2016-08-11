package app.startups.nitrr.ecell.ecellapp.send_otp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.SharedPrefs;
import app.startups.nitrr.ecell.ecellapp.home.view.Home;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.RetrofitOtpProvider;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.RetrofitVerifyProvider;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.VerifyOtpData;
import app.startups.nitrr.ecell.ecellapp.send_otp.presenter.SendOtpPresenter;
import app.startups.nitrr.ecell.ecellapp.send_otp.presenter.SendOtpPresenterImpl;
import app.startups.nitrr.ecell.ecellapp.send_otp.presenter.VerifyOtpPresenter;
import app.startups.nitrr.ecell.ecellapp.send_otp.presenter.VerifyOtpPresenterImpl;

public class SendOtp extends AppCompatActivity implements SendOtpView {
    private ProgressBar progressBar;
    SendOtpPresenter sendOtpPresenter;
    VerifyOtpPresenter verifyOtpPresenter;
    String num1 = "", name = "", lname = "", email = "", college = "", branch = "", sem = "", otp = "", token = "";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__verification);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        Button btn = (Button) findViewById(R.id.btn);

        sendOtpPresenter = new SendOtpPresenterImpl(new RetrofitOtpProvider(), this);
        verifyOtpPresenter = new VerifyOtpPresenterImpl(new RetrofitVerifyProvider(), this);
        Button btn1 = (Button) findViewById(R.id.btn1);
        getIntents();
        Log.d("Response", "1");
        progressBar.setVisibility(View.GONE);
        assert btn != null;
        btn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        EditText num = (EditText) findViewById(R.id.num);
                        num1 = num.getText().toString();
                        Log.d("Response", "2");
                        if (num1.length() != 10) {
                            showMessage("ENTER CORRECT MOBILE NUMBER!");
                        } else
                            sendOtpPresenter.sendOtp(num1, name);
                        Log.d("Response", "3");

                    }
                });
        assert btn1 != null;
        btn1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        EditText verify = (EditText) findViewById(R.id.verify);
                        otp = verify.getText().toString();
                        verifyOtpPresenter.verifyOtp(name, lname, email, college, sem, branch, num1, otp, token);
                    }
                });
    }

    @Override
    public void showLoading(boolean show) {

        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(SendOtp.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOtpSent() {

        EditText verify = (EditText) findViewById(R.id.verify);
        verify.setVisibility(View.VISIBLE);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setVisibility(View.VISIBLE);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setText("Resend Otp");
        i = 1;

    }


    public void getIntents() {
        name = getIntent().getExtras().getString("name").toString();
        lname = getIntent().getExtras().getString("lname").toString();
        email = getIntent().getExtras().getString("email").toString();
        token = getIntent().getExtras().getString("token").toString();
        college = getIntent().getExtras().getString("college").toString();
        branch = getIntent().getExtras().getString("branch").toString();
        sem = getIntent().getExtras().getString("sem").toString();
    }

    @Override
    public void onOtpVerified(VerifyOtpData verifyOtpData) {
        SharedPrefs sharedPrefs = new SharedPrefs(SendOtp.this);
        sharedPrefs.setLogin(true);
        sharedPrefs.setEmailId(email);
        sharedPrefs.setUsername(name + " " + lname);
        sharedPrefs.setPhotoUrl(null);
        sharedPrefs.setFCM(token);
        sharedPrefs.setAccessToken(verifyOtpData.getAccess_token());
        Intent in = new Intent(SendOtp.this, Home.class);
        startActivity(in);
    }
}
