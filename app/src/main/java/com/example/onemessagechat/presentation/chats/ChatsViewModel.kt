package com.example.onemessagechat.presentation.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onemessagechat.domain.entities.chats.Chat
import com.example.onemessagechat.domain.repositories.ChatsRepository
import com.example.onemessagechat.presentation.MainActivity

class ChatsViewModel(private val chatsRepository: ChatsRepository) : ViewModel() {
    val chats: LiveData<List<Chat>> = chatsRepository.getAllChats()

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ChatsViewModel(MainActivity.appModule.chatsRepository) as T
                }
            }
        }
    }
}