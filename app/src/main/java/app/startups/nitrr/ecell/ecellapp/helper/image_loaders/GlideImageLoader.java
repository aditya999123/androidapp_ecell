package app.startups.nitrr.ecell.ecellapp.helper.image_loaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by Meghal on 5/19/2016.
 */
public class GlideImageLoader implements ImageLoader {

    private static final String TAG = "GlideImageLoader";
    Context mContext;
    private RequestManager requestManager;

    public GlideImageLoader(Context mContext) {

        this.mContext = mContext;
        requestManager = Glide.with(mContext);
    }

    @Override
    public void loadImage(String url, final ImageView imageView) {
        Log.d("Response",url);
//        url = url.replace("\"", "");


        requestManager.load(url).crossFade().thumbnail(0.1f);

        requestManager.load(url).crossFade().thumbnail(0.05f);

        requestManager.load(url).crossFade().thumbnail(0.01f)
                //.animate(R.anim.image_animation)
                .into(imageView);


        // This code is used for Round Image View using Glide :)
        /*
        Glide.with(mContext).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {

            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }


        });
*/

    }
    public void load_circular_image(String url,final ImageView imageView){
        Glide.with(mContext).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {

            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(),resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }


        });
    }

}
