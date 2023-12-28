package com.example.markerrecognition.domain.ai

import android.graphics.Bitmap
import com.example.markerrecognition.domain.model.ImageClassification

interface TfLiteImageClassifier {
    fun classify(bitmap: Bitmap, rotation: Int): List<ImageClassification>
}