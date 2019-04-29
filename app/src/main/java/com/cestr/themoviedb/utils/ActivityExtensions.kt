package com.cestr.themoviedb.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

fun  AppCompatActivity.getMultiChoiceAlertDialog(
    context: Context, strArray: Array<String>, strTitle: String,
    callback: (item: Int, isSelected: Boolean) -> Unit,
    cancelCallback: () -> Unit,
    acceptCallback: () -> Unit
): AlertDialog.Builder {

    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setTitle(strTitle)



    alertDialogBuilder.setMultiChoiceItems(strArray, null,
        DialogInterface.OnMultiChoiceClickListener { dialog, selectedItemId, isSelected ->
            callback(selectedItemId, isSelected)

        })
        .setPositiveButton("Done!", DialogInterface.OnClickListener { dialog, id ->
            acceptCallback()
        })
        .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
            cancelCallback()
        })

    return alertDialogBuilder
}

fun AppCompatActivity.getSingleChoiceAlertDialog (context: Activity, items: Array<String>, strTitle: String, callback: (item:Int, isSelected:Boolean) -> Unit
): AlertDialog.Builder {

    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setTitle(strTitle)


    alertDialogBuilder.setSingleChoiceItems(items, 0, DialogInterface.OnClickListener() {
            dialogInterface: DialogInterface, i: Int ->
        run {

            callback(i, true)
        }


    })

    return alertDialogBuilder
}