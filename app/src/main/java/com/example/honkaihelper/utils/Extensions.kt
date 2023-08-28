package com.example.honkaihelper.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.honkaihelper.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

fun <T> ImageView.load(image: T) {
    Glide.with(this).load(image).fitCenter().centerCrop().into(this)
}

fun <T> ImageView.loadImageWithRounded(image: T) {
    Glide.with(this).load(image).centerCrop().into(this)
}