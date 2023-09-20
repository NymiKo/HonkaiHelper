package com.example.honkaihelper.utils

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.honkaihelper.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

const val TOKEN = "token"

fun <T> ImageView.load(image: T) {
    Glide.with(this).load(image).fitCenter().centerCrop().into(this)
}

fun <T> ImageView.loadImageWithRounded(image: T) {
    Glide.with(this).load(image).centerCrop().into(this)
}

fun Fragment.getSharedPrefUser() = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)