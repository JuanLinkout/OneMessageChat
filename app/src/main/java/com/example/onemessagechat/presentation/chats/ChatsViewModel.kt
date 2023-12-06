package com.example.onemessagechat.presentation.chats

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onemessagechat.domain.entities.chats.Chat
import com.example.onemessagechat.domain.repositories.ChatsRepository
import com.example.onemessagechat.domain.repositories.SubscriptionsRepository
import com.example.onemessagechat.presentation.MainActivity
import kotlinx.coroutines.launch

class ChatsViewModel(
    private val chatsRepository: ChatsRepository,
    private val subscriptionsRepository: SubscriptionsRepository
) : ViewModel() {
    // Recebe o live data
    private val chats: LiveData<List<Chat>> = chatsRepository.getAllChats()
    private val _filteredChats = MutableLiveData<List<Chat>>()
    val filteredChats: LiveData<List<Chat>> = _filteredChats

    init {
        // Cria um observer para verificar sempre que o repository atualizar ja atualizar os valores
        // já filtrando quais chats ele está inscrito
        val observer = Observer<List<Chat>> { chatList ->
            viewModelScope.launch {
                val filteredList = chatList.filter { chat ->
                    val subscriptions = subscriptionsRepository.getAllSubscriptions()
                    val foundValue = subscriptions.find { it.subscriptionId == chat.key }
                    foundValue != null
                }
                _filteredChats.value = filteredList
            }
        }

        chats.observeForever(observer)
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ChatsViewModel(
                        MainActivity.appModule.chatsRepository,
                        MainActivity.appModule.subscriptionsRepository
                    ) as T
                }
            }
        }
    }
}