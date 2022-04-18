package com.semicolon.shakeit.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.semicolon.domain.exception.ImageConvertException
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UrlConverter @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun convert(url: String): File {
        return suspendCoroutine {
            Glide.with(context)
                .asFile()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(object : CustomTarget<File>() {
                    override fun onResourceReady(resource: File, transition: Transition<in File>?) {
                        it.resume(resource)
                    }
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        it.resumeWithException(ImageConvertException())
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                        it.resumeWithException(ImageConvertException())
                    }
                })
        }
    }
}