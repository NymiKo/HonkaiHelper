package com.example.tanorami.utils

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.OpenableColumns
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.tanorami.R
import com.example.tanorami.equipment.data.model.Equipment
import java.io.File
import java.io.FileInputStream

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
    when (equipment.rarity) {
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

fun fromHtml(source: String) =
    Html.fromHtml(source, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun Uri.toFile(context: Context): File  {
    var file: File? = null
    context.contentResolver.openFileDescriptor(this, "r", null).use { parcelFileDescriptor ->
        FileInputStream(parcelFileDescriptor?.fileDescriptor).use { inputStream  ->
            val tempFile = File(context.cacheDir, context.contentResolver.getFileName(this))
            tempFile.outputStream().use { fileOutput ->
                inputStream.copyTo(fileOutput)
            }
            file = tempFile
        }
    }
    return checkNotNull(file)
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(newValue = onEvent)
    val lifecycleOwner = rememberUpdatedState(newValue = LocalLifecycleOwner.current)

    DisposableEffect(key1 = lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

fun <T> NavController.popBackStackWithBooleanResult(key: String, value: T?) {
    this.previousBackStackEntry?.savedStateHandle?.set("update", true)
    this.popBackStack()
}