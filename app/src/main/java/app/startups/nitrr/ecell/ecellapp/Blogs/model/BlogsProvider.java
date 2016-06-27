package app.startups.nitrr.ecell.ecellapp.blogs.model;

import app.startups.nitrr.ecell.ecellapp.blogs.OnBlogsReceived;

/**
 * Created by Meghal on 5/29/2016.
 */
public interface BlogsProvider {
    void requestBlogs(OnBlogsReceived onBlogsReceived);
}
