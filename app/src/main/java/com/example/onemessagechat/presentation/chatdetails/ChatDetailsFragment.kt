package com.example.onemessagechat.presentation.chatdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onemessagechat.R

class ChatDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ChatDetailsFragment()
    }

    private lateinit var viewModel: ChatDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}