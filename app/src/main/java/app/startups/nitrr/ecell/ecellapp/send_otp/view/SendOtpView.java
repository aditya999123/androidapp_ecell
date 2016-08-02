package app.startups.nitrr.ecell.ecellapp.send_otp.view;

/**
 * Created by meghal on 2/8/16.
 */
public interface SendOtpView {

    void showLoading(boolean show);
    void showMessage(String message);
    void onOtpSent();

}
