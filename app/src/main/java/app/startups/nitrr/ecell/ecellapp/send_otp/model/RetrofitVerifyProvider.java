package app.startups.nitrr.ecell.ecellapp.send_otp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.send_otp.OnOtpVerify;
import app.startups.nitrr.ecell.ecellapp.send_otp.api.VerifyInterface;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.VerifyOtpData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Iket on 8/8/2016.
 */
public class RetrofitVerifyProvider implements VerifyOtp {

    @Override
    public void verifyOtp(String name, String lname, String email, String college, String sem, String branch, String num, String otp, String fcm, final OnOtpVerify onOtpVerify) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final VerifyInterface verify = retrofit.create(VerifyInterface.class);
        Call<VerifyOtpData> call = verify.getSuccess(name, lname, email, college, sem, branch, num, otp, fcm);
        call.enqueue(new Callback<VerifyOtpData>() {
            @Override
            public void onResponse(Call<VerifyOtpData> call, Response<VerifyOtpData> response) {
                onOtpVerify.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<VerifyOtpData> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
