package com.myubuntu.istestproject.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import com.myubuntu.istestproject.R

object Methods {
    fun getLoader(activity: Activity): Dialog{
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.dialog_loader)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
        dialog.setCancelable(false)

        return dialog
    }
}