package app.startups.nitrr.ecell.ecellapp.home.model;

import app.startups.nitrr.ecell.ecellapp.home.OnHomeDataRequest;
import app.startups.nitrr.ecell.ecellapp.home.api.HomeDetailsRequestInterface;
import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Meghal on 6/19/2016.
 */
public class RetrofitHomeDetailsProvider implements HomeDetailsProvider {
    @Override
    public void requestHomeData(String userId, final OnHomeDataRequest onHomeDataRequest) {


        //        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://townmart.in/AndroidApp/User/")
                //         .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final HomeDetailsRequestInterface homeDetailsRequestInterface = retrofit.create(HomeDetailsRequestInterface.class);

        Call<HomeData> homeDataCall = homeDetailsRequestInterface.getHomeData(userId);

        homeDataCall.enqueue(new Callback<HomeData>() {
            @Override
            public void onResponse(Call<HomeData> call, Response<HomeData> response) {

                onHomeDataRequest.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<HomeData> call, Throwable t) {

                onHomeDataRequest.onFailure();
                t.printStackTrace();
            }
        });


    }
}
