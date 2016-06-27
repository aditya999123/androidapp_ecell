package app.startups.nitrr.ecell.ecellapp.home.view;


import java.util.List;

import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeDetails;

/**
 * Created by Meghal on 6/19/2016.
 */
public interface HomeInterface {

    void showProgressBar(boolean show);
    void showMessage(String message);
    void setData(List<HomeDetails> homeDetailsList);


}
