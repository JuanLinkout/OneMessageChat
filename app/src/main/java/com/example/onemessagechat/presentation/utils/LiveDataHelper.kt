package com.example.onemessagechat.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LiveDataHelper<T> {
    @SuppressLint("InternalInsetResource")
    fun cloneLiveData(originalLiveData: LiveData<T>): LiveData<T> {
        val clonedLiveData = MutableLiveData<T>()

        // Add an observer to the original LiveData
        originalLiveData.observeForever { value ->
            // Update the cloned LiveData whenever the original changes
            clonedLiveData.value = value
        }

        return clonedLiveData
    }
}