package com.example.honkaihelper.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

const val TOKEN = "token"

fun <T> ImageView.load(image: T) = Glide.with(this).load(image).fitCenter().centerCrop().into(this)

fun <T> ImageView.loadWithPlaceholder(image: T, placeholder: Int) =
    Glide.with(this).load(image).fitCenter().centerCrop()
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(placeholder).into(this)

fun Fragment.getDrawable(drawable: Int) =
    AppCompatResources.getDrawable(requireActivity(), drawable)

fun <T> ImageView.loadImageWithRounded(image: T) {
    Glide.with(this).load(image).centerCrop().into(this)
}

fun Fragment.getSharedPrefUser() =
    requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)

fun Fragment.getSharedPrefToken() =
    requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE).getString(TOKEN, "")

fun ContentResolver.getFileName(fileUri: Uri): String {
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}