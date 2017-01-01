package app.startups.nitrr.ecell.ecellapp.sponsers.model;

import app.startups.nitrr.ecell.ecellapp.sponsers.view.OnSponsReceived;

/**
 * Created by Iket on 8/21/2016.
 */
public interface SponsProvider {
    void reqSpons(OnSponsReceived onSponsReceived);
}
