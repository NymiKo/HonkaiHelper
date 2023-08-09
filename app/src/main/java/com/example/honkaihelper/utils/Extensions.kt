package com.example.honkaihelper.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun <T> ImageView.load(image: T) {
    Glide.with(this).load(image).fitCenter().centerCrop().into(this)
}