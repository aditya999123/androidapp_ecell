package app.startups.nitrr.ecell.ecellapp.send_otp.presenter;

/**
 * Created by meghal on 2/8/16.
 */
public interface SendOtpPresenter {


    void sendOtp(String mobile,String name,String url);
    void verifyOtp(String mobile,String otp);
}
