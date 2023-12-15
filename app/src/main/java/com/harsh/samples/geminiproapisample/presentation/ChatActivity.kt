package com.harsh.samples.geminiproapisample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.harsh.samples.geminiproapisample.presentation.theme.GeminiProAPISampleTheme

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiProAPISampleTheme {

            }
        }
    }
}
