package com.example.markerrecognition.data.ai

import android.content.Context
import android.graphics.Bitmap
import com.example.markerrecognition.domain.ai.TfLiteImageClassifier
import com.example.markerrecognition.domain.model.ImageClassification
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.Rot90Op
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import java.lang.IllegalStateException

class TfLiteImageClassifierImpl(
    private val context: Context,
    private val threshold: Float = 0.5f,
    private val maxResult: Int = 1
) : TfLiteImageClassifier {

    private val imgClassifier: ImageClassifier? by lazy {
        val basOptions = BaseOptions.builder()
            .setNumThreads(2)
            .build()
        val options = ImageClassifier.ImageClassifierOptions.builder()
            .setBaseOptions(basOptions)
            .setMaxResults(maxResult)
            .setScoreThreshold(threshold)
            .build()
        try {
            ImageClassifier.createFromFileAndOptions(
                context,
                "landmarks_africa.tflite",
                options
            )
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            null
        }
    }

    override fun classify(bitmap: Bitmap, rotation: Int): List<ImageClassification> {
        val imgProcessor = ImageProcessor
            .Builder()
            .add(Rot90Op(-rotation / 90))
            .build()
        val tensorImage = imgProcessor.process(TensorImage.fromBitmap(bitmap))
        val result = imgClassifier?.classify(tensorImage)

        return result?.flatMap { classification ->
            classification.categories.map { category ->
                ImageClassification(
                    name = category.displayName,
                    score = category.score
                )
            }
        }?.distinctBy { it.name } ?: emptyList()
    }
}