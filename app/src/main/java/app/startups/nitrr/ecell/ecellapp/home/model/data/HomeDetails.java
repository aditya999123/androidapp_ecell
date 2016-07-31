package app.startups.nitrr.ecell.ecellapp.home.model.data;

import java.util.List;

/**
 * Created by Meghal on 6/19/2016.
 */
public class HomeDetails {

    private int type;
    private List<String> viewPagerImageList;
    private String title;
    private String discription;
    private String image;
    private String url;
    private String timestamp;
    private String owner;

    public HomeDetails(int type, List<String> viewPagerImageList, String title, String discription, String image, String url, String timestamp, String owner) {
        this.type = type;
        this.viewPagerImageList = viewPagerImageList;
        this.title = title;
        this.discription = discription;
        this.image = image;
        this.url = url;
        this.timestamp = timestamp;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public int getType() {
        return type;
    }

    public List<String> getViewPagerImageList() {
        return viewPagerImageList;
    }

    public String getTitle() {
        return title;
    }

    public String getDiscription() {
        return discription;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
