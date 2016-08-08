package app.startups.nitrr.ecell.ecellapp.send_otp.presenter;

import app.startups.nitrr.ecell.ecellapp.send_otp.OnOtpVerify;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.VerifyOtp;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.VerifyOtpData;
import app.startups.nitrr.ecell.ecellapp.send_otp.view.SendOtpView;

/**
 * Created by Iket on 8/8/2016.
 */
public class VerifyOtpPresenterImpl implements VerifyOtpPresenter {
    private VerifyOtp verifyOtp;
    private SendOtpView sendOtpView;

    public VerifyOtpPresenterImpl(VerifyOtp verifyOtp, SendOtpView sendOtpView) {
        this.verifyOtp = verifyOtp;
        this.sendOtpView = sendOtpView;
    }

    @Override
    public void verifyOtp(String name, String lname, String email, String college, String sem, String branch, String num, String otp, String fcm) {
        sendOtpView.showLoading(true);
       verifyOtp.verifyOtp(name,lname,email,college,sem,branch,num,otp,fcm, new OnOtpVerify() {
           @Override
           public void onSuccess(VerifyOtpData verifyOtpData) {
               sendOtpView.showMessage(verifyOtpData.getMessage());
               sendOtpView.onOtpVerified();
           }

           @Override
           public void onFailed() {
               sendOtpView.showLoading(false);
               sendOtpView.showMessage("Failed");

           }
       });
    }
}
