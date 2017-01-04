package app.startups.nitrr.ecell.ecellapp.home.presenter;


import android.os.CountDownTimer;

import app.startups.nitrr.ecell.ecellapp.home.OnHomeDataRequest;
import app.startups.nitrr.ecell.ecellapp.home.model.HomeDetailsProvider;
import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeData;
import app.startups.nitrr.ecell.ecellapp.home.view.HomeInterface;

/**
 * Created by Meghal on 6/19/2016.
 */
public class HomePresenterImpl implements HomePresenter {

    private HomeInterface homeInterface;
    private HomeDetailsProvider homeDetailsProvider;
    private CountDownTimer countDownTimer;

    public HomePresenterImpl(HomeInterface homeInterface, HomeDetailsProvider homeDetailsProvider) {
        this.homeDetailsProvider = homeDetailsProvider;
        this.homeInterface = homeInterface;
    }


    @Override
    public void requestHomeData(String userId) {
        homeInterface.showProgressBar(true);
        countDownTimer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                homeInterface.showMessage( "Slow internet connection..");
            }
        }.start();



        homeDetailsProvider.requestHomeData(userId, new OnHomeDataRequest() {
            @Override
            public void onSuccess(HomeData homeData) {
                if (homeData.isSuccess()) {
                    homeInterface.setData(homeData.getHomeDetailsList());
                } else {
                    homeInterface.showMessage(homeData.getMessage());
                }
                homeInterface.showProgressBar(false);
            }

            @Override
            public void onFailure() {
                homeInterface.showMessage("No Internet Connection Available");
            }
        });

    }
}
