package com.harsh.samples.geminiproapisample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.harsh.samples.geminiproapisample.ui.theme.GeminiProAPISampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiProAPISampleTheme {

            }
        }
    }
}
