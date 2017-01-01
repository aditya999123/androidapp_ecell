package app.startups.nitrr.ecell.ecellapp.splash_screen.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.splash_screen.api.SplashScreenRequestApi;
import app.startups.nitrr.ecell.ecellapp.splash_screen.model.data.SplashScreenData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by meghal on 6/8/16.
 */
public class RetrofitSplashScreenProvider implements SplashScreenProvider {


    private Retrofit retrofit;
    private SplashScreenRequestApi splashScreenRequestApi;

    public RetrofitSplashScreenProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        splashScreenRequestApi = retrofit.create(SplashScreenRequestApi.class);
    }

    @Override
    public Observable<SplashScreenData> insertFcm(String fcm) {
        return splashScreenRequestApi.insertFcm(fcm);
    }
}
