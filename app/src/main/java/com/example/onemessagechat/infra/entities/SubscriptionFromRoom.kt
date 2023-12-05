package com.example.onemessagechat.infra.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscription")
data class SubscriptionFromRoom(
    @PrimaryKey
    val subscriptionId: String
)