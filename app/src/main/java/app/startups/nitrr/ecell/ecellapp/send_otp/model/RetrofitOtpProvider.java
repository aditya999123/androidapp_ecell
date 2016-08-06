package app.startups.nitrr.ecell.ecellapp.send_otp.model;

import app.startups.nitrr.ecell.ecellapp.send_otp.OnOtpSent;
import app.startups.nitrr.ecell.ecellapp.send_otp.api.RequestInterface;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.SendOtpData;
import app.startups.nitrr.ecell.ecellapp.send_otp.view.ResponseOtp;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 2/8/16.
 */
public class RetrofitOtpProvider implements OtpProvider {
    @Override
    public void sendOtp(String mobile,String name,String url, OnOtpSent onOtpSent) {
        final SendOtpData sendOtpData=new SendOtpData(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url+"/get_otp/"+name+"/"+mobile)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestInterface request = retrofit.create(RequestInterface.class);

        Call<ResponseOtp> call = request.getSuccess();

      /*  call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {

                OnOtpSent.onSuccess(sendOtpData);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
                OnOtpSent.onFailure();

            }

        });
        */


//        onOtpSent.onSuccess(sendOtpData);
//        onOtpSent.onFailed();
    }
}
