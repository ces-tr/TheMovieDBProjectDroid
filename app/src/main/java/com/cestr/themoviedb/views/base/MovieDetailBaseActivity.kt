package com.cestr.themoviedb.views.base

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.cestr.themoviedb.R
import com.cestr.themoviedb.views.moviedetail.adapter.TrailerItemsRecyclerViewAdapter

open class MovieDetailBaseActivity : BaseActivity() {

    var movieId:Int?=null   ;
    var defaultLanguageSelected:Boolean=true

    lateinit  var trailerListAdapter : TrailerItemsRecyclerViewAdapter

    override fun initProperties() {


    }

    protected fun initTrailersRecyclerView() {


        trailerListAdapter = TrailerItemsRecyclerViewAdapter()

        val recyclerView:RecyclerView =findViewById(R.id.rvTrailersList)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter= trailerListAdapter
        recyclerView.isNestedScrollingEnabled = false;
    }


//    protected fun getMultiChoiceAlertDialog (
//        context: Activity, strArray: Array<String>, strTitle: String,
//        callback: (item: Int, isSelected: Boolean) -> Unit,
//        cancelCallback: () -> Unit,
//        acceptCallback: () -> Unit
//    ): AlertDialog.Builder {
//
//        val alertDialogBuilder = AlertDialog.Builder(context)
//        alertDialogBuilder.setTitle(strTitle)
//
//
//
//        alertDialogBuilder.setMultiChoiceItems(strArray, null,
//            DialogInterface.OnMultiChoiceClickListener { dialog, selectedItemId, isSelected ->
//                callback(selectedItemId, isSelected)
//
//            })
//            .setPositiveButton("Done!", DialogInterface.OnClickListener { dialog, id ->
//                acceptCallback()
//            })
//            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
//                cancelCallback()
//            })
//
//        return alertDialogBuilder
//    }

//    protected fun getSingleChoiceAlertDialog (context: Activity, items: Array<String>, strTitle: String, callback: (item:Int, isSelected:Boolean) -> Unit
//    ): AlertDialog.Builder {
//
//        val alertDialogBuilder = AlertDialog.Builder(context)
//        alertDialogBuilder.setTitle(strTitle)
//
//
//        alertDialogBuilder.setSingleChoiceItems(items, 0, DialogInterface.OnClickListener() {
//                dialogInterface: DialogInterface, i: Int ->
//            run {
//
//                callback(i, true)
//            }
//
//
//        })
//
//        return alertDialogBuilder
//    }
}