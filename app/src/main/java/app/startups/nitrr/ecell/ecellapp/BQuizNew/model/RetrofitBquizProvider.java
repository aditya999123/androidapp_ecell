package app.startups.nitrr.ecell.ecellapp.BQuizNew.model;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.api.BquizRequestInterface;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter.BQuizPresenter;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meghal on 6/8/16.
 */
public class RetrofitBquizProvider implements BQuizPresenter {

    private Retrofit retrofit;
    private BquizRequestInterface bquizRequestInterface;

    RetrofitBquizProvider() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bquizRequestInterface = retrofit.create(BquizRequestInterface.class);

    }

    @Override
    public void getBquizData(String adminToken) {

        Call<BQuizData> bQuizDataCall = bquizRequestInterface.getBQuizData(adminToken);

        bQuizDataCall.enqueue(new Callback<BQuizData>() {
            @Override
            public void onResponse(Call<BQuizData> call, Response<BQuizData> response) {

            }

            @Override
            public void onFailure(Call<BQuizData> call, Throwable t) {

            }
        });
    }
}
