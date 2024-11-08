package com.example.tanorami.core.data.image_loader

import android.content.Context
import com.example.tanorami.core.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import javax.inject.Inject

class FileManagerImpl @Inject constructor(
    private val context: Context,
    private val okHttpClient: OkHttpClient,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : FileManager {
    override suspend fun saveImage(
        imageUrl: String,
        child: String,
    ): String = withContext(ioDispatcher) {
        val directory = File(context.filesDir, child)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val file = File(directory, getImageNameFromUrl(imageUrl))

        try {
            val request = Request.Builder().url(imageUrl).build()
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IOException("Failed to download file: $response")
                }
                response.body?.byteStream()?.use { inputStream ->
                    FileOutputStream(file).use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return@withContext file.absolutePath
    }

    private fun getImageNameFromUrl(imageUrl: String): String {
        val uri = URL(imageUrl).path
        return uri.substring(uri.lastIndexOf('/') + 1)
    }
}