package com.example.honkaihelper.utils

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.OpenableColumns
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.honkaihelper.R
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.heroes.data.model.Hero

const val TOKEN = "token"
const val USER = "USER"
const val DB = "DB"

fun <T> ImageView.load(image: T) = Glide.with(this).load(image).fitCenter().centerCrop().into(this)
fun ImageView.loadImageRarity(rarity: Boolean) =
    if (rarity) loadImageWithoutScale(R.drawable.icon_5_stars) else loadImageWithoutScale(R.drawable.icon_4_stars)

fun <T> ImageView.loadWithPlaceholder(image: T, placeholder: Int) =
    Glide.with(this).load(image).fitCenter().centerCrop()
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(placeholder).into(this)

fun <T> ImageView.loadImageWithoutScale(image: T) {
    Glide.with(this).load(image).into(this)
}

fun Fragment.getSharedPrefUser() =
    requireActivity().getSharedPreferences(USER, Context.MODE_PRIVATE)

fun Fragment.getSharedPrefToken() =
    requireActivity().getSharedPreferences(USER, Context.MODE_PRIVATE).getString(TOKEN, "")

fun Activity.getSharedPrefVersion() = getSharedPreferences(DB, Context.MODE_PRIVATE)

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

fun ImageView.backgroundHero(hero: Hero) {
    this.load(hero.avatar)
    if (hero.rarity) {
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

fun ImageView.backgroundRarity(rarity: Int) {
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