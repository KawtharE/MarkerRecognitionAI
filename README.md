# MarkerRecognitionAI
AI Marker recognition with Tensorflow Lite and CameraX

![ezgif com-speed (1)](https://github.com/KawtharE/MarkerRecognitionAI/assets/19794865/5f0c3318-cbc5-4ee0-9347-e6a423c1d712)

## Key Steps:
1. Downolad Landmark tflite model file from https://www.kaggle.com/models/google/landmarks/frameworks/tfLite
  in my case i have _landmarks_classifier_africa_
2. In Android studio, create asserts directory and add the tflite file there
3. Add gradle dependencies for **CameraX** and **TenserFlow**:
   
    ```
      // CameraX core library using the camera2 implementation
      val cameraxVersion = "1.4.0-alpha03"
      implementation("androidx.camera:camera-core:${cameraxVersion}")
      implementation("androidx.camera:camera-camera2:${cameraxVersion}")
      implementation("androidx.camera:camera-lifecycle:${cameraxVersion}")
      implementation("androidx.camera:camera-video:${cameraxVersion}")
      implementation("androidx.camera:camera-view:${cameraxVersion}")
      implementation("androidx.camera:camera-extensions:${cameraxVersion}")
    ```

    ```
     // Tensorflow Lite dependencies
      implementation("org.tensorflow:tensorflow-lite-task-vision:0.4.2")
      // Import the GPU delegate plugin Library for GPU inference
      implementation("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.0")
      implementation("org.tensorflow:tensorflow-lite-gpu:2.9.0")
    ```
    => you could also use the tenserFlow dependencie integrated recently in **play-service**

4. In AndroidManifest, add camera permission:
    ```
      <uses-feature
          android:name="android.hardware.camera"
          android:required="false" />
      <uses-permission android:name="android.permission.CAMERA"/>
    ```
5. For UI, since in Compose we don't have yet ``PreviewView`` composable, i wrapped in ``AndroidView`` to create the ``CameraPreview``
    
