package app.startups.nitrr.ecell.ecellapp.about_us.view;

import java.util.List;

/**
 * Created by Iket on 8/20/2016.
 */
public class ResponseAboutUs {

    private List<AboutUsData> aboutus;

    public ResponseAboutUs(List<AboutUsData> aboutus) {
        this.aboutus = aboutus;
    }

    public List<AboutUsData> getAboutus() {
        return aboutus;
    }
}
