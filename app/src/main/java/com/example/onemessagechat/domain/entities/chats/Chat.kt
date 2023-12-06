package com.example.onemessagechat.domain.entities.chats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chat(
    val key: String = "",
    val message: String = ""
) : Parcelable
