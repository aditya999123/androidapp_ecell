package app.startups.nitrr.ecell.ecellapp.blogs;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.blogs.data.BlogData;

/**
 * Created by Meghal on 5/30/2016.
 */
public interface OnBlogsReceived {

    void onSuccess(List<BlogData> blogDataList);

    void onFailure();

}
