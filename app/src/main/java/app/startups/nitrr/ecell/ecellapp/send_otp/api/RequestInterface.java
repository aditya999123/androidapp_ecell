package app.startups.nitrr.ecell.ecellapp.send_otp.api;


import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.SendOtpData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Iket on 8/6/2016.
 */
public interface RequestInterface {
    @FormUrlEncoded
    @POST(Urls.SEND_OTP)
    Call<SendOtpData> getSuccess (@Field("name") String name, @Field("mobile") String mobile);
}
