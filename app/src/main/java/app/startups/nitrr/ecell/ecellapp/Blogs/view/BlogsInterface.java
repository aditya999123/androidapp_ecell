package app.startups.nitrr.ecell.ecellapp.blogs.view;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.blogs.data.BlogData;

/**
 * Created by Meghal on 5/29/2016.
 */
public interface BlogsInterface {

    void ShowProgressBar(boolean show);
    void SetData(List<BlogData> blogDataList);


}
