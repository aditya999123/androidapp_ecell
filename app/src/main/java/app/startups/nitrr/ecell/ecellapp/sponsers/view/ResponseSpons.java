package app.startups.nitrr.ecell.ecellapp.sponsers.view;

import java.util.List;

/**
 * Created by Iket on 8/21/2016.
 */
public class ResponseSpons {
    private List<SponsData> spons;

    public ResponseSpons(List<SponsData> spons) {
        this.spons = spons;
    }

    public List<SponsData> getSpons() {
        return spons;
    }
}
