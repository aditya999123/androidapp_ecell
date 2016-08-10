package app.startups.nitrr.ecell.ecellapp.send_otp.api;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.VerifyOtpData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Iket on 8/8/2016.
 */
public interface VerifyInterface {
    @FormUrlEncoded
    @POST(Urls.VERIFY_OTP)
    Call<VerifyOtpData> getSuccess (@Field("name") String name, @Field("lname") String lname, @Field("email") String email, @Field("college") String college, @Field("sem") String sem, @Field("branch") String branch, @Field("mobile") String num, @Field("otp") String otp, @Field("fcm") String fcm);
}
