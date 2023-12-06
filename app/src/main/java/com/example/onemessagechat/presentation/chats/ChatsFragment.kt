package com.example.onemessagechat.presentation.chats

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onemessagechat.R
import com.example.onemessagechat.databinding.FragmentChatsBinding
import com.example.onemessagechat.domain.entities.chats.Chat
import com.example.onemessagechat.domain.entities.navigation.ChatDetailsTypeEnum
import com.example.onemessagechat.presentation.adapters.AdapterOnClickListener
import com.example.onemessagechat.presentation.adapters.ChatItemAdapter
import com.example.onemessagechat.presentation.utils.StatusBarUtil

class ChatsFragment : Fragment() {
    private lateinit var binding: FragmentChatsBinding
    private val viewModel by viewModels<ChatsViewModel>(factoryProducer = { ChatsViewModel.Factory() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatsBinding.inflate(layoutInflater)

        viewModel.filteredChats.observe(viewLifecycleOwner) {
            binding.chatsRecyclerView.adapter =
                ChatItemAdapter(it.toTypedArray(), AdapaterCallback())

            binding.createChatButton.setOnClickListener {
                binding.root.findNavController().navigate(
                    ChatsFragmentDirections.actionChatsFragmentToChatDetailsFragment(
                        Chat(key = "", message = ""),
                        ChatDetailsTypeEnum.CREATE
                    )
                )
            }

            binding.subscribeButton.setOnClickListener {
                binding.root.findNavController().navigate(
                    ChatsFragmentDirections.actionChatsFragmentToSubscribeFragment()
                )
            }
        }

        val statusBarHeight = StatusBarUtil.getStatusBarHeight(binding.root.context)
        binding.chatsRecyclerView.setPadding(16, 16 + statusBarHeight, 16, 16)
        binding.chatsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        binding.chatsRecyclerView.adapter?.notifyDataSetChanged()
    }

    inner class AdapaterCallback : AdapterOnClickListener {
        override fun onItemClick(position: Int) {
            val list = viewModel.filteredChats.value
            val chat = list?.find { it.key == list[position].key } ?: return
            val action =
                ChatsFragmentDirections.actionChatsFragmentToChatDetailsFragment(
                    chat,
                    ChatDetailsTypeEnum.EDIT
                )
            binding.root.findNavController().navigate(action)
        }
    }

}