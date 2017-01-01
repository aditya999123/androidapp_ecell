package app.startups.nitrr.ecell.ecellapp.sponsers.view;

import java.util.List;

/**
 * Created by Iket on 8/21/2016.
 */
public interface OnSponsReceived {
    void onFailure();
    void onSuccess(List<SponsData> sponsDataList);
}
