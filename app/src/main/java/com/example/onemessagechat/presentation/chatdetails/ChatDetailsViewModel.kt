package com.example.onemessagechat.presentation.chatdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.onemessagechat.domain.entities.chats.Chat
import com.example.onemessagechat.domain.entities.subscribe.Subscription
import com.example.onemessagechat.domain.repositories.ChatsRepository
import com.example.onemessagechat.domain.repositories.SubscriptionsRepository
import com.example.onemessagechat.presentation.MainActivity
import kotlinx.coroutines.launch

class ChatDetailsViewModel(
    private val chatsRepository: ChatsRepository,
    private val subscriptionsRepository: SubscriptionsRepository
) : ViewModel() {
    fun createOrUpdate(key: String, message: String, isEditing: Boolean) {
        viewModelScope.launch {

            chatsRepository.createOrUpdate(Chat(key, message))

            if (!isEditing) {
                val response = subscriptionsRepository.getAllSubscriptions()
                val foundItem = response.find { it.subscriptionId ==  key}
                if (foundItem == null) subscriptionsRepository.createSubscription(Subscription(key))
            }
        }
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ChatDetailsViewModel(
                        MainActivity.appModule.chatsRepository,
                        MainActivity.appModule.subscriptionsRepository
                    ) as T
                }
            }
        }
    }
}