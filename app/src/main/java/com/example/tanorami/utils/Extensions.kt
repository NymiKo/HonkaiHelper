package com.example.tanorami.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.OpenableColumns
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tanorami.R
import com.example.tanorami.equipment.data.model.Equipment

const val TOKEN = "token"
const val USER = "USER"
const val DB = "DB"

fun <T> ImageView.load(image: T) = Glide.with(this).load(image).fitCenter().centerCrop().into(this)
fun ImageView.loadImageRarity(rarity: Boolean) =
    if (rarity) loadImageWithoutScale(R.drawable.icon_5_stars) else loadImageWithoutScale(R.drawable.icon_4_stars)

fun <T> ImageView.loadWithPlaceholder(image: T, placeholder: Int) =
    Glide.with(this).load(image).fitCenter().centerCrop()
        .placeholder(placeholder).into(this)

fun <T> ImageView.loadImageWithoutScale(image: T) {
    Glide.with(this).load(image).into(this)
}

fun Fragment.getSharedPrefUser() =
    requireActivity().getSharedPreferences(USER, Context.MODE_PRIVATE)

fun Fragment.getSharedPrefToken() =
    requireActivity().getSharedPreferences(USER, Context.MODE_PRIVATE).getString(TOKEN, "")

fun Activity.getSharedPrefVersion() = getSharedPreferences(DB, Context.MODE_PRIVATE)
fun Fragment.getSharedPrefVersion() = requireActivity().getSharedPreferences(DB, Context.MODE_PRIVATE)

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

fun String.uppercaseFirstChar() = this.replaceFirstChar { it.uppercase() }

fun ImageView.backgroundHero(rarity: Boolean) {
    if (rarity) {
        this.background = ContextCompat.getDrawable(this.context, R.color.orange)
    } else {
        this.background = ContextCompat.getDrawable(this.context, R.color.violet)
    }
}

fun ImageView.backgroundEquipment(equipment: Equipment) {
    this.load(equipment.image)
    when (equipment.rarity.toInt()) {
        0 -> this.background = ContextCompat.getDrawable(this.context, R.color.blue)
        1 -> this.background = ContextCompat.getDrawable(this.context, R.color.violet)
        2 -> this.background = ContextCompat.getDrawable(this.context, R.color.orange)
    }
}

fun View.backgroundRarity(rarity: Int) {
    when (rarity) {
        0 -> this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(R.color.blue))
        1 -> this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(R.color.violet))
        2 -> this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(R.color.orange))
    }
}

fun ImageView.backgroundWeapon(rarity: Int) {
    when (rarity) {
        0 -> this.background = ContextCompat.getDrawable(this.context, R.color.blue)
        1 -> this.background = ContextCompat.getDrawable(this.context, R.color.violet)
        2 -> this.background = ContextCompat.getDrawable(this.context, R.color.orange)
    }
}

fun <T : Parcelable> Fragment.getParcelable(arg: String, clazz: Class<T>) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireArguments().getParcelable(arg, clazz)
    } else {
        requireArguments().getParcelable(arg)
    }

fun <T : Parcelable> Fragment.getParcelableArrayList(arg: String, clazz: Class<T>) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireArguments().getParcelableArrayList(arg, clazz)
    } else {
        requireArguments().getParcelableArrayList(arg)
    }

fun fromHtml(source: String) =
    Html.fromHtml(source, HtmlCompat.FROM_HTML_MODE_LEGACY)

@ColorInt
@SuppressLint("Recycle")
fun Context.themeColor(
    @AttrRes themeAttrId: Int
): Int {
    return obtainStyledAttributes(
        intArrayOf(themeAttrId)
    ).use {
        it.getColor(0, Color.MAGENTA)
    }
}