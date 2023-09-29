package com.example.honkaihelper.utils

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.honkaihelper.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

const val TOKEN = "token"

fun <T> ImageView.load(image: T) = Glide.with(this).load(image).fitCenter().centerCrop().into(this)

fun <T> ImageView.loadWithPlaceholder(image: T, placeholder: Int) = Glide.with(this).load(image).placeholder(placeholder).into(this)

fun Fragment.getDrawable(drawable: Int) = AppCompatResources.getDrawable(requireActivity(), drawable)

fun <T> ImageView.loadImageWithRounded(image: T) {
    Glide.with(this).load(image).centerCrop().into(this)
}

fun Fragment.getSharedPrefUser() = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)

fun <T> Fragment.getNavigationResult(key: String = "RESULT") =
    findNavController().currentBackStackEntry?.savedStateHandle?.get<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}