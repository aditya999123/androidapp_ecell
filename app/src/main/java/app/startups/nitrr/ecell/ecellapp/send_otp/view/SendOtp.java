package app.startups.nitrr.ecell.ecellapp.send_otp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__verification);
        Log.d("Response","test");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mobile Verification");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        sendOtpPresenter = new SendOtpPresenterImpl(new RetrofitOtpProvider(), this);
        verifyOtpPresenter = new VerifyOtpPresenterImpl(new RetrofitVerifyProvider(), this);
        getIntents();
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn= (Button) findViewById(R.id.btn);
        assert btn != null;
        final EditText num = (EditText) findViewById(R.id.num);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        num1 = num.getText().toString();
                        if (num1.length() != 10) {
                            showMessage("ENTER CORRECT MOBILE NUMBER!");
                        } else
                            sendOtpPresenter.sendOtp(num1, name);
                    }
                });
        assert btn1 != null;
       final EditText verify = (EditText) findViewById(R.id.verify);
        otp=verify.getText().toString();
        verify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                otp=verify.getText().toString();
                if(otp.length()==4)
                    hide_keyboard();
            }
        });

        btn1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        otp = verify.getText().toString();
                        verifyOtpPresenter.verifyOtp(name, lname, email, college, sem, branch, num1, otp, token);
                    }
                });
    }
    void hide_keyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn= (Button) findViewById(R.id.btn);
        EditText verify = (EditText) findViewById(R.id.verify);
        verify.setVisibility(View.VISIBLE);
        btn1.setVisibility(View.VISIBLE);
        btn.setText("Resend Otp");
        countDown(30);
        btn.setClickable(false);
        TextView resend_timer= (TextView) findViewById(R.id.resend_timer);
        resend_timer.setVisibility(View.VISIBLE);


    }

    public void countDown(int time) {

        time *= 1000;
        new CountDownTimer(time,1000) {
            TextView resend_timer= (TextView) findViewById(R.id.resend_timer);
            Button btn=(Button)findViewById(R.id.btn);
            public void onTick(long millisUntilFinished) {

                resend_timer.setText("You can resend otp in " + (millisUntilFinished / 1000) % 60+" seconds");

            }

            public void onFinish() {
                btn.setClickable(true);
                resend_timer.setVisibility(View.GONE);
            }
        }.start();

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
    public void onOtpFailed() {
        Toast.makeText(SendOtp.this, "Otp Verification Failed.Try Again", Toast.LENGTH_SHORT).show();
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
        finish();
    }
}
