package com.example.onemessagechat.di

import android.content.Context
import com.example.onemessagechat.data.repositories.ChatsRepositoryImplementation
import com.example.onemessagechat.data.repositories.SubscriptionsRepositoryImplementation
import com.example.onemessagechat.domain.repositories.ChatsRepository
import com.example.onemessagechat.domain.repositories.SubscriptionsRepository
import com.example.onemessagechat.infra.AppDatabase

interface AppModule {
    val chatsRepository: ChatsRepository
    val subscriptionsRepository: SubscriptionsRepository
}

class AppModuleImplementation(
    private val appContext: Context
) : AppModule {
    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getDatabase(appContext)
    }

    override val chatsRepository: ChatsRepository by lazy {
        ChatsRepositoryImplementation(subscriptionsRepository)
    }

     override val subscriptionsRepository: SubscriptionsRepository by lazy {
        SubscriptionsRepositoryImplementation(appDatabase.subscriptionDAO())
    }
}