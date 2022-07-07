package com.inmobly.common_ui.utils.extensions

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.inmobly.common_ui.R


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE

val EditText.value
    get() = text?.toString() ?: ""

fun ImageView.loadUrl(url: String?) {
    Glide.with(context).load(url).error(R.drawable.placeholder).placeholder(R.drawable.placeholder)
        .into(this)
}