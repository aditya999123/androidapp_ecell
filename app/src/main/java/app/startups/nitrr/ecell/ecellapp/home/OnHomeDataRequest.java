package app.startups.nitrr.ecell.ecellapp.home;


import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeData;

/**
 * Created by Meghal on 6/19/2016.
 */
public interface OnHomeDataRequest {

    void onSuccess(HomeData homeData);

    void onFailure();

}
