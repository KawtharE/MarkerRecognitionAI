package com.example.markerrecognition.presentation.utils

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.markerrecognition.domain.ai.TfLiteImageClassifier
import com.example.markerrecognition.domain.model.ImageClassification

class TfLiteImageAnalyzer(
    private val imgClassifier: TfLiteImageClassifier,
    private val onResult: (List<ImageClassification>) -> Unit
) : ImageAnalysis.Analyzer {

    private var frameCounter = 0

    // analyze image every one sec to save battery
    override fun analyze(image: ImageProxy) {
        if (frameCounter % 60 == 0) {
            val rotation = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .cropCenter(321, 321)

            val result = imgClassifier.classify(bitmap, rotation)
            onResult(result)
        }
        frameCounter++
        image.close()
    }
}