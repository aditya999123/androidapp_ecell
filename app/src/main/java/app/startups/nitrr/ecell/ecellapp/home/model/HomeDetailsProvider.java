package app.startups.nitrr.ecell.ecellapp.home.model;


import app.startups.nitrr.ecell.ecellapp.home.OnHomeDataRequest;

/**
 * Created by Meghal on 6/19/2016.
 */
public interface HomeDetailsProvider {

    void requestHomeData(String userId, OnHomeDataRequest onHomeDataRequest);

}
