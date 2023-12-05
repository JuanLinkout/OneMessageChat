package com.example.onemessagechat.domain.entities.chats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chat(
    var key: String,
    var message: String
) : Parcelable
