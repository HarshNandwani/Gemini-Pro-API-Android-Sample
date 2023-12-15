package com.harsh.samples.geminiproapisample.presentation

import com.harsh.samples.geminiproapisample.domain.model.ChatMessage

data class UiState(
    val allMessages: List<ChatMessage> = emptyList(),
    var currentPrompt: String = "",
    var responseState: ResponseState = ResponseState.GENERATED
)

enum class ResponseState {
    GENERATING, GENERATED, ERROR
}
