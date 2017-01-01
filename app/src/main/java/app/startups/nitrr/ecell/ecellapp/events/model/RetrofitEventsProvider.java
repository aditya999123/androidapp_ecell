package app.startups.nitrr.ecell.ecellapp.events.model;

import app.startups.nitrr.ecell.ecellapp.events.api.RequestInterface;
import app.startups.nitrr.ecell.ecellapp.events.view.OnEventsReceived;
import app.startups.nitrr.ecell.ecellapp.events.view.jsonResponse;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Iket on 7/27/2016.
 */
public class RetrofitEventsProvider implements EventsProvider {


    @Override
    public void requestEvents(final OnEventsReceived onEventsReceived) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final RequestInterface request = retrofit.create(RequestInterface.class);

        Call<jsonResponse> call = request.getEvents();

        call.enqueue(new Callback<jsonResponse>() {
            @Override
            public void onResponse(Call<jsonResponse> call, Response<jsonResponse> response) {
                onEventsReceived.onSuccess(response.body().getEvents());
            }

            @Override
            public void onFailure(Call<jsonResponse> call, Throwable t) {
                t.printStackTrace();
                onEventsReceived.onFailure();

            }

        });

    }

}
