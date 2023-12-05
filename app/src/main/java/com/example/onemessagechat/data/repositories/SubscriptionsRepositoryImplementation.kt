package com.example.onemessagechat.data.repositories

import com.example.onemessagechat.domain.entities.subscribe.Subscription
import com.example.onemessagechat.domain.repositories.SubscriptionsRepository
import com.example.onemessagechat.infra.daos.SubscriptionDAO
import com.example.onemessagechat.infra.entities.SubscriptionFromRoom

class SubscriptionsRepositoryImplementation(private val subscriptionDAO: SubscriptionDAO): SubscriptionsRepository {
    override suspend fun getAllSubscriptions(): List<Subscription> {
        return subscriptionDAO.getAllSubscriptions().map {
            Subscription(it.subscriptionId)
        }
    }

    override suspend fun createSubscription(subscription: Subscription) {
        subscriptionDAO.insertSubscription(SubscriptionFromRoom(subscription.subscriptionId))
    }
}