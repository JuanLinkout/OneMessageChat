package com.example.onemessagechat.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onemessagechat.databinding.ActivityMainBinding
import com.example.onemessagechat.di.AppModule
import com.example.onemessagechat.di.AppModuleImplementation

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        appModule = AppModuleImplementation(this)
        setContentView(binding.root)
    }
}