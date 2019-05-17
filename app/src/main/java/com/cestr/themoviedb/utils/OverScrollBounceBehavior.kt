package com.cestr.themoviedb.utils

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewCompat
import android.view.ViewGroup
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat.setTranslationY
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.DebugUtils
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import kotlin.math.abs


class OverScrollBounceBehavior : CoordinatorLayout.Behavior<View> {

    private var mOverScrollY: Int = 0
    private var rvmoviesTrasY:Float = 0.0f

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int
    ): Boolean {
        mOverScrollY = 0
        rvmoviesTrasY=0f
        return true
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        if (dyUnconsumed == 0 ) {
            return
        }

        mOverScrollY -= dyUnconsumed



        if(mOverScrollY > 0   ) {
            return
        }
        Log.d("mOverScrollY",mOverScrollY.toString())

        val swiperefreshvgroup = target as ViewGroup

         //rvmoviesTrasY  = 0.0F

        val swiperefreshvgroupcount = swiperefreshvgroup.childCount

        for (i in 0 until swiperefreshvgroupcount) {

            val view = swiperefreshvgroup.getChildAt(i)
            if(view is RecyclerView){
                rvmoviesTrasY = view.translationY

               Log.d("recyclerviewY",view.translationY.toString())
            }
//            view.translationY = mOverScrollY.toFloat()- 10
        }

        val group = child as ViewGroup
        val count = group.childCount
        for (i in 0 until count) {
            val view = group.getChildAt(i)
            if(view is ProgressBar && rvmoviesTrasY < 0) {

                view.translationY = rvmoviesTrasY + (rvmoviesTrasY*0.725f)
            }
        }
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View) {
        val group = child as ViewGroup
        val count = group.childCount
        for (i in 0 until count) {
            val view = group.getChildAt(i)
            if(view is ProgressBar && view.translationY < 0  && rvmoviesTrasY < 0) {
//                view.translationY = mOverScrollY.toFloat()- 10
                ViewCompat.animate(view).translationY(-view.height.toFloat()) //.start()
            }
        }
    }
}