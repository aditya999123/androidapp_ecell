package app.startups.nitrr.ecell.ecellapp.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class Cache {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isNetworkAvailable(MyApplication.getContext())) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(10,TimeUnit.DAYS).build();
                request = request.newBuilder().cacheControl(cacheControl).build();
            }
            return chain.proceed(request);

        }
    };

    public static okhttp3.Cache provideCache(){

        okhttp3.Cache cache=null;
        try {
            cache = new okhttp3.Cache(new File(MyApplication.getContext().getCacheDir(), "Responses"), 10 * 1024 * 1024);
        }
        catch (Exception e){
            Log.e("Extra","Could not create cache"+e.toString());
            e.printStackTrace();

        }

        return cache;

    }

}
