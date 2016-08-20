package app.startups.nitrr.ecell.ecellapp.about_us.model;

import app.startups.nitrr.ecell.ecellapp.about_us.view.OnAboutusReceived;

/**
 * Created by Iket on 8/20/2016.
 */
public interface AboutUsProvider {
    void requestData(OnAboutusReceived onAboutussReceived);

}
