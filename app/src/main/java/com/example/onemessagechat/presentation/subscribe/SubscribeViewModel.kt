package com.example.onemessagechat.presentation.subscribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.onemessagechat.domain.entities.subscribe.Subscription
import com.example.onemessagechat.domain.repositories.SubscriptionsRepository
import com.example.onemessagechat.presentation.MainActivity
import kotlinx.coroutines.launch

class SubscribeViewModel(private val subscriptionsRepository: SubscriptionsRepository) :
    ViewModel() {
    fun create(key: String) {
        viewModelScope.launch {
            subscriptionsRepository.createSubscription(Subscription(key))
        }
    }

    companion object {
        fun Factory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SubscribeViewModel(
                        MainActivity.appModule.subscriptionsRepository
                    ) as T
                }
            }
        }
    }
}