package app.startups.nitrr.ecell.ecellapp.home.model.data;

import java.util.List;

/**
 * Created by Meghal on 6/19/2016.
 */
public class HomeData {
    private boolean success;
    private String message;
    private List<HomeDetails> homeDetailsList;

    public HomeData(boolean success, String message, List<HomeDetails> homeDetailsList) {

        this.success = success;
        this.message = message;
        this.homeDetailsList = homeDetailsList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<HomeDetails> getHomeDetailsList() {
        return homeDetailsList;
    }

}
