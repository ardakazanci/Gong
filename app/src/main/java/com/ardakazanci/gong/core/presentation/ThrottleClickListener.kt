package com.ardakazanci.gong.core.presentation

import android.view.View

fun View.setOnThrottledClickListener(clickListener: View.OnClickListener) {
    var lastClickTime: Long = 0

    setOnClickListener {
        val clickedTime = System.currentTimeMillis()
        if (clickedTime - lastClickTime < 500L) {
            return@setOnClickListener
        }
        clickListener.onClick(it)
        lastClickTime = clickedTime
    }
}