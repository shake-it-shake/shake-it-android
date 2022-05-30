package com.semicolon.shakeit.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.loader.content.CursorLoader
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun makeToast(context: Context, message: String) =
    Toast.makeText(context, message, LENGTH_SHORT).show()

suspend fun fetchImage(context: Context): File? = suspendCoroutine {
    TedImagePicker.with(context)
        .mediaType(MediaType.IMAGE)
        .showTitle(false)
        .cancelListener { it.resume(null) }
        .errorListener { _ -> it.resume(null) }
        .start { uri ->
            it.resume(File(getRealPathFromURI(uri, context)!!))
        }
}

private fun getRealPathFromURI(uri: Uri, context: Context): String? {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val loader = CursorLoader(context, uri, proj, null, null, null)
    val cursor: Cursor = loader.loadInBackground()!!
    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
    cursor.moveToFirst()
    val result = cursor.getString(columnIndex)
    cursor.close()
    return result
}

fun fetchTimeGap(localDateTime: LocalDateTime): Int {
    val currentTime = LocalDateTime.now(ZoneId.systemDefault())
    val gap = currentTime.toEpochSecond(ZoneOffset.UTC)
        .minus(localDateTime.toEpochSecond(ZoneOffset.UTC))
    return (gap / 60).toInt()
}
