package app.startups.nitrr.ecell.ecellapp.send_otp.view;

import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.VerifyOtpData;

/**
 * Created by meghal on 2/8/16.
 */
public interface SendOtpView {

    void showLoading(boolean show);
    void showMessage(String message);
    void onOtpSent();
    void getIntents();
    void onOtpFailed();

    void onOtpVerified(VerifyOtpData verifyOtpData);
}
