package com.harsh.samples.geminiproapisample.data.repository

import com.harsh.samples.geminiproapisample.domain.model.ChatMessage
import com.harsh.samples.geminiproapisample.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/*
* This sample stores chats in memory. You can use Room persistence.
* */
class InMemoryChatRepository: ChatRepository {

    private val messages = MutableStateFlow(emptyList<ChatMessage>())


    override fun saveMessage(chatMessage: ChatMessage) {
        messages.update {
            messages.value.toMutableList().apply { this.add(chatMessage) }
        }
    }

    override fun getAllMessages(): List<ChatMessage> {
        return messages.value.toMutableList()
    }

    override fun subscribeMessages(): Flow<List<ChatMessage>> {
        return messages
    }
}
