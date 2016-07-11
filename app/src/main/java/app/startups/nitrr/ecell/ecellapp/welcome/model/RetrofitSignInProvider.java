package app.startups.nitrr.ecell.ecellapp.welcome.model;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.welcome.model.data.SignInData;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by meghal on 8/7/16.
 */
public class RetrofitSignInProvider implements SignInProvider {

    private Retrofit retrofit;

    RetrofitSignInProvider(){
      /*  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
*/

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
  //              .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        orderDetailsRequestApi = retrofit.create(OrderDetailsRequestApi.class);

    }

    @Override
    public Observable<SignInData> requestSignIn(String userId, String username, String email, String profilePhoto, String profileUrl, int signInType) {
        return null;
    }
}
