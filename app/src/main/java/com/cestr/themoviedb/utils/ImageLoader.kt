package com.cestr.themoviedb.utils

import android.graphics.Color
import android.support.v7.content.res.AppCompatResources
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cestr.themoviedb.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

object ImageLoader {

    fun loadImageInto(imageView: ImageView, url: String, animated: Boolean) {

        val shimmer = Shimmer.ColorHighlightBuilder().setHighlightColor(Color.LTGRAY).build()
        val shimmerDrawable = ShimmerDrawable()
        shimmerDrawable.setShimmer(shimmer)

        val reqOptoins = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .format(DecodeFormat.PREFER_ARGB_8888)
            .placeholder(shimmerDrawable)
            .fitCenter()
            .error(AppCompatResources.getDrawable(imageView.context, R.drawable.movie_placeholder))

        if (!animated) {
            reqOptoins.placeholder(AppCompatResources.getDrawable(imageView.context, R.drawable.movie_placeholder))
                .dontAnimate()
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