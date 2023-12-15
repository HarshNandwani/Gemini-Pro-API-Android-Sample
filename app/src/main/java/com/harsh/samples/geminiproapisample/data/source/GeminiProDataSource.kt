package com.harsh.samples.geminiproapisample.data.source

import com.google.ai.client.generativeai.GenerativeModel

class GeminiProDataSource(
    private val apiKey: String
) {

    private val geminiProModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = apiKey
    )

    // 1 chat support only, create more if you need
    private val chat = geminiProModel.startChat()

    suspend fun generateIndividualResponse(prompt: String): String {
        return try {
            val response = geminiProModel.generateContent(prompt).text
            response ?: ""
        } catch (e: Exception) {
            e.localizedMessage ?: "Unable to generate response"
        }
    }

    suspend fun generateOngoingChatResponse(message: String): String {
        return try {
            val response = chat.sendMessage(message).text
            response ?: ""
        } catch (e: Exception) {
            e.localizedMessage ?: "Unable to generate response"
        }
    }

}
