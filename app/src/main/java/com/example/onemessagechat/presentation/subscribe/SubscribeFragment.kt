package com.example.onemessagechat.presentation.subscribe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onemessagechat.databinding.FragmentSubscribeBinding

class SubscribeFragment : Fragment() {
    private lateinit var binding: FragmentSubscribeBinding
    private val viewModel by viewModels<SubscribeViewModel>(factoryProducer = { SubscribeViewModel.Factory() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubscribeBinding.inflate(layoutInflater)

        binding.confirmButton.setOnClickListener {
            viewModel.create(binding.chatKeyEditText.text.toString())
            findNavController().popBackStack()
        }

        return binding.root
    }
}