package com.example.honkaihelper.data.image_loader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(
    private val context: Context
): ImageLoader {
    override fun downloadAndSaveImage(imageUrl: String, child: String, fileName: String): String {
        val directory = File(context.getExternalFilesDir(null), child)
        directory.mkdirs()
        val file = File(directory, fileName)

        Glide.with(context)
            .asBitmap()
            .load(imageUrl)
            .apply(RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    try {
                        val outputStream = FileOutputStream(file)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            resource.compress(Bitmap.CompressFormat.WEBP_LOSSY, 100, outputStream)
                        } else {
                            resource.compress(Bitmap.CompressFormat.WEBP, 100, outputStream)
                        }
                        outputStream.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        return file.absolutePath
    }
}