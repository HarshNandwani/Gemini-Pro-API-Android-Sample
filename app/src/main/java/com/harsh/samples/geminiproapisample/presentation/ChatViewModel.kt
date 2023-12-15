package com.harsh.samples.geminiproapisample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harsh.samples.geminiproapisample.domain.model.ChatMessage
import com.harsh.samples.geminiproapisample.domain.model.Role
import com.harsh.samples.geminiproapisample.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    private var listenMessagesJob: Job? = null

    fun sendMessage() {
        if (listenMessagesJob == null) listenForResponses()
        _state.value.responseState = ResponseState.GENERATING
        val prompt = state.value.currentPrompt
        _state.value.currentPrompt = ""
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveMessage(ChatMessage(0, prompt, Role.USER, Date()))
        }
    }

    fun onPromptChange(prompt: String) {
        if (_state.value.responseState == ResponseState.GENERATING) return
        _state.value = _state.value.copy(currentPrompt = prompt)
    }

    private fun listenForResponses() {
        listenMessagesJob = viewModelScope.launch {
            repository.subscribeMessages().collect {
                if (it.lastOrNull()?.role == Role.MODEL)
                    _state.value.responseState = ResponseState.GENERATED
                _state.value = _state.value.copy(allMessages = it)
            }
        }
    }

}
