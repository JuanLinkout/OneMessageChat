package com.example.onemessagechat.infra.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.onemessagechat.infra.entities.SubscriptionFromRoom

@Dao
interface SubscriptionDAO {
    @Insert
    suspend fun insertSubscription(subscription: SubscriptionFromRoom)

    @Query("SELECT * FROM `subscription`")
    suspend fun getAllSubscriptions(): List<SubscriptionFromRoom>
}