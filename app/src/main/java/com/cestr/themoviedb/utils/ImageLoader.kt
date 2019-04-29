package com.cestr.themoviedb.utils

import android.support.v7.content.res.AppCompatResources
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cestr.themoviedb.R

object ImageLoader {

    fun loadImageInto(imageView: ImageView, url: String, animate: Boolean) {

        val reqOptoins = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .format(DecodeFormat.PREFER_ARGB_8888)
            .placeholder(AppCompatResources.getDrawable(imageView.context, R.drawable.movie_placeholder))
            .error(AppCompatResources.getDrawable(imageView.context, R.drawable.movie_placeholder))

        if (!animate) {
            reqOptoins.dontAnimate()
        }
        val bitmapRequestBuilder =
            Glide.with(imageView.context )
                .asBitmap()
                .apply(reqOptoins)
                .load(url)
//                    .format(DecodeFormat.PREFER_ARGB_8888)
//                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                    .error(ContextCompat.getDrawable(  imageView.context , R.drawable.placeholder_marketplace ))

        bitmapRequestBuilder.into(imageView)
    }


}