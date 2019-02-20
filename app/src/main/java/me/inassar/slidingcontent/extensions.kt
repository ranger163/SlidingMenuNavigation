package me.inassar.slidingcontent

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Toast

fun View.enableAnimatedBackground() {
    val animationDrawable = background as AnimationDrawable
    animationDrawable.apply {
        setEnterFadeDuration(2000)
        setExitFadeDuration(4000)
        start()
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}