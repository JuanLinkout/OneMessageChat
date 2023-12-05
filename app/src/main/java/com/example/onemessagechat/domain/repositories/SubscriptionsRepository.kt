package com.example.onemessagechat.domain.repositories

import com.example.onemessagechat.domain.entities.subscribe.Subscription

interface SubscriptionsRepository {
    suspend fun getAllSubscriptions(): List<Subscription>
    suspend fun createSubscription(subscription: Subscription): Unit
}