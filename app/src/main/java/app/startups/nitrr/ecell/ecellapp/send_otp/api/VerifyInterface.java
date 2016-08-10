package app.startups.nitrr.ecell.ecellapp.send_otp.api;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.VerifyOtpData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Iket on 8/8/2016.
 */
public interface VerifyInterface {
    @GET(Urls.VERIFY_OTP)
    Call<VerifyOtpData> getSuccess (@Query("name") String name, @Query("lname") String lname, @Query("email") String email, @Query("college")String college, @Query("sem")String sem, @Query("branch") String branch, @Query("mobile")String num, @Query("otp") String otp, @Query("fcm") String fcm);
}
