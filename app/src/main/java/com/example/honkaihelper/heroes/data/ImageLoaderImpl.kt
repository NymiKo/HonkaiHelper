package com.example.honkaihelper.heroes.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(
    private val context: Context
): ImageLoader {
    override fun downloadAndSaveImage(imageUrl: String): String {
        val fileName = "hero_${System.currentTimeMillis()}.jpg"
        val directory = File(context.getExternalFilesDir(null), "images")
        directory.mkdirs()
        val file = File(directory, fileName)

        // Используйте Glide для загрузки изображения и сохранения его
        Glide.with(context)
            .asBitmap()
            .load(imageUrl)
            .apply(RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    try {
                        val outputStream = FileOutputStream(file)
                        resource.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                        outputStream.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })

        return file.absolutePath
    }
}