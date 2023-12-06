package com.example.onemessagechat.presentation.chats

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.onemessagechat.domain.entities.chats.Chat
import com.example.onemessagechat.domain.repositories.ChatsRepository
import com.example.onemessagechat.presentation.MainActivity
import kotlinx.coroutines.launch

class ChatsViewModel(private val chatsRepository: ChatsRepository) : ViewModel() {
    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>> = _chats

    fun fetchData() {
        viewModelScope.launch {
            val response = chatsRepository.getAllChats()
            _chats.value = response
        }
    }

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