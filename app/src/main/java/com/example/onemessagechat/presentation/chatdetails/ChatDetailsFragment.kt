package com.example.onemessagechat.presentation.chatdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onemessagechat.databinding.FragmentChatDetailsBinding
import com.example.onemessagechat.domain.entities.navigation.ChatDetailsTypeEnum

class ChatDetailsFragment : Fragment() {
    private val args: ChatDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentChatDetailsBinding
    private val viewModel by viewModels<ChatDetailsViewModel>(factoryProducer = { ChatDetailsViewModel.Factory() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatDetailsBinding.inflate(layoutInflater)

        if (args.type == ChatDetailsTypeEnum.EDIT) {
            binding.chatKeyEditText.setText(args.chat.key)
            binding.chatKeyEditText.isEnabled = false
            binding.chatMessageEditText.setText(args.chat.message)

            binding.confirmButton.setOnClickListener {
               viewModel.createOrUpdate(
                    binding.chatKeyEditText.text.toString(),
                    binding.chatMessageEditText.text.toString(),
                   true
                )

                findNavController().popBackStack()
            }
        } else if (args.type == ChatDetailsTypeEnum.CREATE) {
            binding.confirmButton.setOnClickListener {
               viewModel.createOrUpdate(
                    binding.chatKeyEditText.text.toString(),
                    binding.chatMessageEditText.text.toString(),
                   false
                )

                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}