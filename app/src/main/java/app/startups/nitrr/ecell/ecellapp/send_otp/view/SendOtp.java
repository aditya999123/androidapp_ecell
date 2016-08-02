package app.startups.nitrr.ecell.ecellapp.send_otp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.RetrofitOtpProvider;
import app.startups.nitrr.ecell.ecellapp.send_otp.presenter.SendOtpPresenter;
import app.startups.nitrr.ecell.ecellapp.send_otp.presenter.SendOtpPresenterImpl;

public class SendOtp extends AppCompatActivity implements SendOtpView {

    SendOtpPresenter sendOtpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        sendOtpPresenter=new SendOtpPresenterImpl(new RetrofitOtpProvider(),this);
        sendOtpPresenter.sendOtp("81235678");
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(SendOtp.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOtpSent() {

        // otp is sent show the otp verify layout layout.setvisiblity();
    }
}
