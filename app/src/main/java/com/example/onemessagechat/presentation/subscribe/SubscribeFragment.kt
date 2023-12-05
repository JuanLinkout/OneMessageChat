package com.example.onemessagechat.presentation.subscribe

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onemessagechat.R

class SubscribeFragment : Fragment() {

    companion object {
        fun newInstance() = SubscribeFragment()
    }

    private lateinit var viewModel: SubscribeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subscribe, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubscribeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}