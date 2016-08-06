package app.startups.nitrr.ecell.ecellapp.send_otp.api;


import app.startups.nitrr.ecell.ecellapp.send_otp.view.ResponseOtp;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Iket on 8/6/2016.
 */
public interface RequestInterface {
    @GET("/")
    Call<ResponseOtp> getSuccess();
}
