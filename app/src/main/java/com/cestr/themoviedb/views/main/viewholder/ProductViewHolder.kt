package com.cestr.testkotlin.views.main

import android.support.v7.widget.RecyclerView
import android.view.View

import android.widget.TextView
import com.cestr.themoviedb.model.MovieModel
import com.cestr.themoviedb.views.MovieImageView
import kotlinx.android.synthetic.main.cell_movies_grid.view.*


class ImageViewHolder(paramView: View) : RecyclerView.ViewHolder(paramView) {

//    var mButtonFastBuy: ToggleButton? = null

    var mImageViewProduct: MovieImageView? = null

    var mTextViewProductCampaignName: TextView? = null

    var mTextViewProductName: TextView? = null

    var mTextViewProductNumberInstallments: TextView? = null

    var mTextViewProductOldPrice: TextView? = null

    var mTextViewProductParcel: TextView? = null

    var mTextViewProductPrice: TextView? = null

    var mTextViewProductPrice_Mx: TextView? = null

    var mTextViewProductStock: TextView? = null

    private var view: View? = null

        init {
            view= paramView
        }

        fun bind(productDataBean: MovieModel, paramInt: Int) {

//                itemView.product_name_textView.text = productDataBean.name
                itemView.movie_photo_imageView.loadUrl(productDataBean.imgUrl)

//            itemView.u = "User id: $userId"
        }



    fun getView(): View {
                return this.itemView!!
    }


}