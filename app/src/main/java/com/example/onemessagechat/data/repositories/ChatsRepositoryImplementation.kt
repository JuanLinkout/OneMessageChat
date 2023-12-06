package com.example.onemessagechat.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val _chatList: MutableLiveData<List<Chat>> = MutableLiveData()
    val chatList: LiveData<List<Chat>> get() = _chatList

    init{
        // Realiza as alterações quando algum valor for alterado/adicionado
        dbFbReference.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chat: Chat? = snapshot.getValue<Chat>()
                chat?.also { cont ->
                    if (!chatList.value.orEmpty().any { it.key == cont.key }) {
                        _chatList.postValue(chatList.value.orEmpty() + cont)
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val chat: Chat? = snapshot.getValue<Chat>()
                chat?.also { editedChat ->
                    val updatedList = chatList.value.orEmpty().map {
                        if (editedChat.key == it.key) editedChat else it
                    }
                    _chatList.postValue(updatedList)
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val removedChat: Chat? = snapshot.getValue<Chat>()
                removedChat?.let { chat ->
                    val updatedList = chatList.value.orEmpty().filter { it.key != chat.key }
                    _chatList.postValue(updatedList)
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })

        // Carrega toda a lista quando o app abrir
        dbFbReference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatMap = snapshot.getValue<Map<String, Chat>>()
                chatMap?.values?.also{ _chatList.postValue(chatList.value.orEmpty() + it) }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override suspend fun createOrUpdate(chat: Chat) {
        dbFbReference.child(chat.key).setValue(chat)
    }

    override fun getAllChats(): LiveData<List<Chat>> = chatList
}