package app.startups.nitrr.ecell.ecellapp.Blogs.api;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.Blogs.data.BlogData;
import app.startups.nitrr.ecell.ecellapp.Blogs.data.BlogFeed;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Meghal on 5/30/2016.
 */
public interface RequestInterface {
    @GET("/AndroidAll/Project/loadBlogs.php")
    Call<BlogFeed> getBlog();
}
