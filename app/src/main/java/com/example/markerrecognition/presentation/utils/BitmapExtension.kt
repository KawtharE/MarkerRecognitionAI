package com.example.markerrecognition.presentation.utils

import android.graphics.Bitmap

fun Bitmap.cropCenter(newWidth: Int, newHeight: Int): Bitmap {
    val xStart = (width - newWidth) / 2
    val yStart = (height - newHeight) / 2

    if (xStart < 0 || yStart < 0 || newWidth > width || newHeight > height) {
        throw IllegalArgumentException("Invalid arguments for center crop")
    }
    return Bitmap.createBitmap(this, xStart, yStart, newWidth, newHeight)
}