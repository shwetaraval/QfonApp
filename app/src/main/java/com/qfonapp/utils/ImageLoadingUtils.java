package com.qfonapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;


public class ImageLoadingUtils {

    /*public static RequestOptions optionProfile = new RequestOptions().placeholder(R.drawable.ic_light_bulb).error(R.drawable.ic_light_bulb);*/


    public static void loadImage(Context context, ImageView imageView, String imagePath) {
        Glide.with(context)
                .load(imagePath)
                .into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, String imagePath, RequestOptions option) {
        Glide.with(context)
                .load(imagePath)
                .apply(option)
                .into(imageView);
    }

    public static void loadImage(ImageView imageView, String imagePath, @DrawableRes int placeHolder, @DrawableRes int error) {
        if (imageView == null)
            return;
        Glide.with(imageView.getContext())
                .load(imagePath)
                .apply(new RequestOptions().placeholder(placeHolder).error(error))
                .into(imageView);
    }

    public static void loadImage(ImageView imageView, String imagePath, @DrawableRes int placeHolder) {
        loadImage(imageView, imagePath, placeHolder, placeHolder);
    }

    public static void loadImage(Context context, ImageView imageView, String imagePath, RequestOptions option, RequestListener<Drawable> listener) {
        Glide.with(context)
                .load(imagePath)
                .apply(option)
                .listener(listener)
                .into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, int drawable) {
        Glide.with(context)
                .load(drawable)
                .into(imageView);
    }

}
