package app.startups.nitrr.ecell.ecellapp.send_otp.api;


import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.SendOtpData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Iket on 8/6/2016.
 */
public interface RequestInterface {
    @GET(Urls.SEND_OTP)
    Call<SendOtpData> getSuccess (@Query("name") String name,@Query("mobile") String mobile);
}
