package app.startups.nitrr.ecell.ecellapp.blogs.data;

import java.util.List;

/**
 * Created by Meghal on 5/30/2016.
 */
public class BlogFeed {

    private List<BlogData> blogs;

    BlogFeed(List<BlogData> blogs){
        this.blogs=blogs;
    }

    public List<BlogData> getBlogs() {
        return blogs;
    }
}
