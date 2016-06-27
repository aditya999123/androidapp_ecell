package app.startups.nitrr.ecell.ecellapp.blogs.api;

import app.startups.nitrr.ecell.ecellapp.blogs.data.BlogFeed;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Meghal on 5/30/2016.
 */
public interface RequestInterface {
    @GET("/AndroidAll/Project/loadBlogs.php")
    Call<BlogFeed> getBlog();
}
