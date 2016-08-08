package app.startups.nitrr.ecell.ecellapp.send_otp.model;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.send_otp.OnOtpSent;
import app.startups.nitrr.ecell.ecellapp.send_otp.api.RequestInterface;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.SendOtpData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 2/8/16.
 */
public class RetrofitOtpProvider implements OtpProvider {
    @Override
    public void sendOtp(String mobile, String name, final OnOtpSent onOtpSent) {
        final SendOtpData sendOtpData=new SendOtpData(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestInterface request = retrofit.create(RequestInterface.class);

        Call<SendOtpData> call = request.getSuccess(name,mobile);
        call.enqueue(new Callback<SendOtpData>() {
            @Override
            public void onResponse(Call<SendOtpData> call, Response<SendOtpData> response) {
                onOtpSent.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SendOtpData> call, Throwable t) {

            }
        });




    }
}
