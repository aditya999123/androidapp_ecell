package app.startups.nitrr.ecell.ecellapp.contact_us.model;

import app.startups.nitrr.ecell.ecellapp.contact_us.api.RequestInterface;
import app.startups.nitrr.ecell.ecellapp.contact_us.view.JsonResponse;
import app.startups.nitrr.ecell.ecellapp.contact_us.view.OnContactsReceived;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Iket on 8/20/2016.
 */
public class RetrofitRequestProvider implements ContactsProvider {


    @Override
    public void requestContacts(final OnContactsReceived onContactsReceived) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        final RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JsonResponse> call = request.getContacts();

        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                onContactsReceived.onSuccess(response.body().getContacts());
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
            onContactsReceived.onFailure();
            }
        });



    }
}
