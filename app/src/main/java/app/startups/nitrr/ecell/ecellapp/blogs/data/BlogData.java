package app.startups.nitrr.ecell.ecellapp.blogs.data;

/**
 * Created by Meghal on 5/30/2016.
 */
public class BlogData {

    String blogId;
    String blogTitle;
    String blogCategory;
    String blogBody;
    String blogDate;
    String blogTime;
    String image;
    String blogOwner;
    String blogImage;

    BlogData(String blogId, String blogTitle, String blogTime, String blogOwner, String blogImage
            , String image, String blogDate, String blogCategory, String blogBody) {
        this.blogBody = blogBody;
        this.blogTitle = blogTitle;
        this.blogTime = blogTime;
        this.blogOwner = blogOwner;
        this.blogImage = blogImage;
        this.blogId = blogId;
        this.blogDate = blogDate;
        this.blogCategory = blogCategory;
        this.image = image;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public String getBlogCategory() {
        return blogCategory;
    }

    public String getBlogDate() {
        return blogDate;
    }

    public String getBlogId() {
        return blogId;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public String getBlogOwner() {
        return blogOwner;
    }

    public String getBlogTime() {
        return blogTime;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public String getImage() {
        return image;
    }
}
