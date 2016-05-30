package app.startups.nitrr.ecell.ecellapp.Blogs.model;

import app.startups.nitrr.ecell.ecellapp.Blogs.OnBlogsReceived;

/**
 * Created by Meghal on 5/29/2016.
 */
public interface BlogsProvider {
    void requestBlogs(OnBlogsReceived onBlogsReceived);
}
