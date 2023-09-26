package com.ardakazanci.gong.core.presentation

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.WindowManager
import com.ardakazanci.gong.R

class ProgressUtils(val activity: Activity?) {

    private lateinit var mAlertDialog: AlertDialog

    fun showProgress() {
        val mDialogView = LayoutInflater.from(activity).inflate(R.layout.base_progress, null)

        val mBuilder = activity?.let {
            AlertDialog.Builder(it)
                .setView(mDialogView)
                .setCancelable(false)
        }

        mBuilder?.let {
            mAlertDialog = it.show()
        }

        mAlertDialog.window?.run {
            setBackgroundDrawableResource(android.R.color.transparent)
            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
    }

    fun hideProgress() {
        if (::mAlertDialog.isInitialized) {
            mAlertDialog.dismiss()
        }
    }
}