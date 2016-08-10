package app.startups.nitrr.ecell.ecellapp.BQuizNew.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.api.SubmitAnswerRequestInterface;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.SubmitAnswerData;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by meghal on 9/8/16.
 */
public class RetrofitSubmitAnswerProvider implements SubmitAnswerProvider {

    Retrofit retrofit;
    SubmitAnswerRequestInterface submitAnswerRequestInterface;

    RetrofitSubmitAnswerProvider() {

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

        submitAnswerRequestInterface = retrofit.create(SubmitAnswerRequestInterface.class);


    }

    @Override
    public Observable<SubmitAnswerData> submitQuestion(String questionId, String answerId) {

        return submitAnswerRequestInterface.submitAnswer(questionId, answerId);
    }
}
