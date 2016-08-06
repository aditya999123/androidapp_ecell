package app.startups.nitrr.ecell.ecellapp.events.model;

import android.util.Log;

import app.startups.nitrr.ecell.ecellapp.events.api.RequestInterface;
import app.startups.nitrr.ecell.ecellapp.events.view.OnEventsReceived;
import app.startups.nitrr.ecell.ecellapp.events.view.jsonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Iket on 7/27/2016.
 */
public class RetrofitEventsProvider implements EventsProvider {
    private static final String TAG = "Retrofit Blogs Provider";

    @Override
    public void requestEvents(final OnEventsReceived onEventsReceived) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://iket0512.esy.es")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestInterface request = retrofit.create(RequestInterface.class);

        Call<jsonResponse> call = request.getEvents();

        call.enqueue(new Callback<jsonResponse>() {
            @Override
            public void onResponse(Call<jsonResponse> call, Response<jsonResponse> response) {
                Log.i(TAG, "ResponseOtp Received :" + response.body().getEvents().toString());
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
