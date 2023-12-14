package com.harsh.samples.geminiproapisample.domain.model

import java.util.Date

data class ChatMessage(
    val id: Long,
    val message: String,
    val role: Role,
    val timestamp: Date
)

enum class Role {
    USER, MODEL
}
