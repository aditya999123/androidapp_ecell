package app.startups.nitrr.ecell.ecellapp.Blogs.model;

import android.util.Log;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.Blogs.OnBlogsReceived;
import app.startups.nitrr.ecell.ecellapp.Blogs.api.RequestInterface;
import app.startups.nitrr.ecell.ecellapp.Blogs.data.BlogData;
import app.startups.nitrr.ecell.ecellapp.Blogs.data.BlogFeed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Meghal on 5/29/2016.
 */
public class RetrofitBlogsProvider implements BlogsProvider {

    private static final String TAG ="Retrofit Blogs Provider" ;

    @Override
    public void requestBlogs(final OnBlogsReceived onBlogsReceived) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.vegknock.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestInterface request = retrofit.create(RequestInterface.class);

        Call<BlogFeed> call = request.getBlog();

        call.enqueue(new Callback<BlogFeed>() {
            @Override
            public void onResponse(Call<BlogFeed> call, Response<BlogFeed> response) {
                Log.i(TAG,"Response Received :"+response.body().getBlogs().toString() );
                onBlogsReceived.onSuccess(response.body().getBlogs());
            }

            @Override
            public void onFailure(Call<BlogFeed> call, Throwable t) {

                t.printStackTrace();
                onBlogsReceived.onFailure();
            }
        });

    }
}
