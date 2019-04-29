package com.cestr.themoviedb.views

import android.content.Context
import android.support.annotation.Nullable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.ViewTreeObserver
import android.widget.ImageView
import com.cestr.themoviedb.R
import com.cestr.themoviedb.utils.ImageLoader
import com.cestr.themoviedb.utils.ImageUrlProvider


class MovieImageView (context: Context?, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    private var finalsize: Int = 185

    private var imageUrl:String?=""


    init{

        val a = context?.obtainStyledAttributes(
            attrs,
            R.styleable.MovieImageViewProperties
        )

        val imageSize = a?.getInt(R.styleable.MovieImageViewProperties_imageSize, 0)
        if (imageSize!! > 0) {
            finalsize = imageSize
        }

    }

    private fun loadImageUrl() {

            ImageLoader.loadImageInto(

                this as ImageView, ImageUrlProvider.getUrlForSize(finalsize, this.imageUrl!!),true
            )
        }

    fun loadUrl(@Nullable mImageUrl: String) {

        this.imageUrl = mImageUrl

        if (this.imageUrl == null) {
            this.setImageResource(R.drawable.movie_placeholder)
            return
        }
        this.loadImageUrl()
    }

}

