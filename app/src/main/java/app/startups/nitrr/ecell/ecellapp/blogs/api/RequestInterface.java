package app.startups.nitrr.ecell.ecellapp.blogs.api;

import app.startups.nitrr.ecell.ecellapp.blogs.data.BlogFeed;
import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Meghal on 5/30/2016.
 */
public interface RequestInterface {
    @GET(Urls.REQUEST_BLOGS)
    Call<BlogFeed> getBlog();
}
