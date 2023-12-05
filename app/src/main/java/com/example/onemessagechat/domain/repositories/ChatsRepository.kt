package com.example.onemessagechat.domain.repositories

import com.example.onemessagechat.domain.entities.chats.Chat

interface ChatsRepository {
    suspend fun create(chat: Chat): Unit
    suspend fun edit(chat: Chat): Unit
    suspend fun getAllChats(): List<Chat>
}