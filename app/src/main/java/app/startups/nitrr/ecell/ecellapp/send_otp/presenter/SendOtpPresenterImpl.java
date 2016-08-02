package app.startups.nitrr.ecell.ecellapp.send_otp.presenter;

import app.startups.nitrr.ecell.ecellapp.send_otp.OnOtpSent;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.OtpProvider;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.SendOtpData;
import app.startups.nitrr.ecell.ecellapp.send_otp.view.SendOtpView;

/**
 * Created by meghal on 2/8/16.
 */
public class SendOtpPresenterImpl implements SendOtpPresenter{

    private OtpProvider otpProvider;
    private SendOtpView sendOtpView;

    public SendOtpPresenterImpl(OtpProvider otpProvider, SendOtpView sendOtpView) {
        this.otpProvider = otpProvider;
        this.sendOtpView = sendOtpView;
    }

    @Override
    public void sendOtp(String mobile) {

        sendOtpView.showLoading(true);
        otpProvider.sendOtp(mobile, new OnOtpSent() {


            @Override
            public void onSuccess(SendOtpData sendOtpData) {
                sendOtpView.showMessage(sendOtpData.getMessage());
            }

            @Override
            public void onFailed() {

                sendOtpView.showLoading(false);
                sendOtpView.showMessage("Failed");
            }
        });
    }

    @Override
    public void verifyOtp(String mobile, String otp) {

    }
}
