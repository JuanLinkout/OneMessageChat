package com.example.onemessagechat.domain.repositories

import androidx.lifecycle.LiveData
import com.example.onemessagechat.domain.entities.chats.Chat

interface ChatsRepository {
    suspend fun createOrUpdate(chat: Chat): Unit
    fun getAllChats(): LiveData<List<Chat>>
}