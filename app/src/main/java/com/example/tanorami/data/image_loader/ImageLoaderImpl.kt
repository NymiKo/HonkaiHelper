package com.example.tanorami.data.image_loader

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) : ImageLoader {
    override suspend fun downloadAndSaveImage(
        imageUrl: String,
        child: String,
        fileName: String
    ): String = withContext(ioDispatcher) {
        val directory = File(context.getExternalFilesDir(null), child)
        directory.mkdirs()
        val file = File(directory, fileName)

        val result = try {
            val resource = Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .apply(
                    RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                )
                .submit()
                .get()

            val compressFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Bitmap.CompressFormat.WEBP_LOSSY
            } else {
                Bitmap.CompressFormat.WEBP
            }

            FileOutputStream(file).use { outputStream ->
                resource.compress(compressFormat, 80, outputStream)
            }

            file.absolutePath
        } catch (e: Exception) {
            null
        }

        return@withContext result ?: ""
    }
}