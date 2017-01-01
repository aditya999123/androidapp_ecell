package app.startups.nitrr.ecell.ecellapp.blogs.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.startups.nitrr.ecell.ecellapp.blogs.OnBlogsReceived;
import app.startups.nitrr.ecell.ecellapp.blogs.api.RequestInterface;
import app.startups.nitrr.ecell.ecellapp.blogs.data.BlogFeed;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Meghal on 5/29/2016.
 */
public class RetrofitBlogsProvider implements BlogsProvider {

    private static final String TAG = "Retrofit Blogs Provider";

    @Override
    public void requestBlogs(final OnBlogsReceived onBlogsReceived) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final RequestInterface request = retrofit.create(RequestInterface.class);

        Call<BlogFeed> call = request.getBlog();

        call.enqueue(new Callback<BlogFeed>() {
            @Override
            public void onResponse(Call<BlogFeed> call, Response<BlogFeed> response) {
                onBlogsReceived.onSuccess(response.body().getBlogs());
            }

            @Override
            public void onFailure(Call<BlogFeed> call, Throwable t) {
                Log.d("Response","Fail");
                t.printStackTrace();
                onBlogsReceived.onFailure();
            }
        });

    }
}
