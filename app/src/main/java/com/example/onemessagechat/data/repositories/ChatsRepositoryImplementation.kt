package com.example.onemessagechat.data.repositories

import com.example.onemessagechat.domain.entities.chats.Chat
import com.example.onemessagechat.domain.repositories.ChatsRepository
import com.example.onemessagechat.domain.repositories.SubscriptionsRepository
import com.google.firebase.Firebase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class ChatsRepositoryImplementation(private val subscriptionsRepository: SubscriptionsRepository): ChatsRepository {
    companion object {
        private const val CHAT_LIST_ROOT_NODE = "chat_list_node"
    }

    private val dbFbReference = Firebase.database.getReference(CHAT_LIST_ROOT_NODE)

    private val chatList: MutableList<Chat> = mutableListOf()

    init{
        // Realiza as alterações quando algum valor for alterado/adicionado
        dbFbReference.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chat: Chat? = snapshot.getValue<Chat>()
                chat?.also { cont -> if (!chatList.any { it.key == cont.key }) chatList.add(cont) }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val chat: Chat? = snapshot.getValue<Chat>()
                chat?.also { editedChat ->
                    val index = chatList.indexOfFirst { editedChat.key == it.key }
                    chatList.apply { this[index] = editedChat }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })

        // Carrega toda a lista quando o app abrir
        dbFbReference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatMap = snapshot.getValue<Map<String, Chat>>()
                chatList.clear()
                chatMap?.values?.also{ chatList.addAll(it) }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override suspend fun create(chat: Chat) {
        TODO("Not yet implemented")
    }

    override suspend fun edit(chat: Chat) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllChats(): List<Chat> = chatList.filter { chat ->
        val subscriptions = subscriptionsRepository.getAllSubscriptions()
        val foundValue = subscriptions.find { it.subscriptionId == chat.key }
        foundValue != null
    }

}