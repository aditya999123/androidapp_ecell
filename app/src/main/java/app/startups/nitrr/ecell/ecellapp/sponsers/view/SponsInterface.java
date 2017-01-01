package app.startups.nitrr.ecell.ecellapp.sponsers.view;

import java.util.List;

/**
 * Created by Iket on 8/21/2016.
 */
public interface SponsInterface {
    void showLoading(boolean show);
    void showMessage(String message);
    void setData(List<SponsData> sponsDataList);

}
