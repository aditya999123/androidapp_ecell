package app.startups.nitrr.ecell.ecellapp.blogs.presenter;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.blogs.OnBlogsReceived;
import app.startups.nitrr.ecell.ecellapp.blogs.data.BlogData;
import app.startups.nitrr.ecell.ecellapp.blogs.model.BlogsProvider;
import app.startups.nitrr.ecell.ecellapp.blogs.view.BlogsInterface;

/**
 * Created by Meghal on 5/29/2016.
 */
public class BlogsPresenterImpl implements BlogsPresenter {
    private BlogsProvider blogsProvider;
    private BlogsInterface blogsInterface;

    public BlogsPresenterImpl(BlogsInterface blogsInterface, BlogsProvider blogsProvider) {

        this.blogsProvider = blogsProvider;
        this.blogsInterface = blogsInterface;

    }


    @Override
    public void requestBlogs() {

        blogsInterface.ShowProgressBar(true);
        blogsProvider.requestBlogs(new OnBlogsReceived() {
            @Override
            public void onSuccess(List<BlogData> blogDataList) {

                blogsInterface.SetData(blogDataList);
                blogsInterface.ShowProgressBar(false);
            }

            @Override
            public void onFailure() {
                blogsInterface.ShowProgressBar(false);

            }
        });

    }
}
