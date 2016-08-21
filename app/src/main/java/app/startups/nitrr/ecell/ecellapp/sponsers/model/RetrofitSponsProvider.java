package app.startups.nitrr.ecell.ecellapp.sponsers.model;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.sponsers.api.RequestApiSpons;
import app.startups.nitrr.ecell.ecellapp.sponsers.view.OnSponsReceived;
import app.startups.nitrr.ecell.ecellapp.sponsers.view.ResponseSpons;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Iket on 8/21/2016.
 */
public class RetrofitSponsProvider implements SponsProvider {

    @Override
    public void reqSpons(final OnSponsReceived onSponsReceived) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final RequestApiSpons request = retrofit.create(RequestApiSpons.class);
        Call<ResponseSpons> call=request.getSpons();

        call.enqueue(new Callback<ResponseSpons>() {
            @Override
            public void onResponse(Call<ResponseSpons> call, Response<ResponseSpons> response) {
                onSponsReceived.onSuccess(response.body().getSpons());
            }

            @Override
            public void onFailure(Call<ResponseSpons> call, Throwable t) {
                onSponsReceived.onFailure();
                t.printStackTrace();
            }
        });


    }
}
