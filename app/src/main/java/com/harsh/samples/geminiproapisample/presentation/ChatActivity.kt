package com.harsh.samples.geminiproapisample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harsh.samples.geminiproapisample.presentation.theme.GeminiProAPISampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiProAPISampleTheme {
                val viewModel: ChatViewModel = hiltViewModel()
                val uiState by viewModel.state.collectAsStateWithLifecycle()

                ChatScreen(
                    uiState,
                    { viewModel.onPromptChange(it) },
                    { viewModel.sendMessage() }
                )
            }
        }
    }
}
