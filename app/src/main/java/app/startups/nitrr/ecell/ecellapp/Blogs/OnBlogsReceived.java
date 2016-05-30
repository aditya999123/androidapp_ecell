package app.startups.nitrr.ecell.ecellapp.Blogs;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.Blogs.data.BlogData;
import app.startups.nitrr.ecell.ecellapp.Blogs.data.BlogFeed;

/**
 * Created by Meghal on 5/30/2016.
 */
public interface OnBlogsReceived {

    void onSuccess(List<BlogData> blogDataList);

    void onFailure();

}
