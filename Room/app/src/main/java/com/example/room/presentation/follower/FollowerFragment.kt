package com.example.room.presentation.follower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.room.R
import com.example.room.databinding.FragmentFollowerBinding
import com.example.room.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : BindingFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {

    private lateinit var followerAdapter: FollowerAdapter
    private val followerViewModel by viewModels<FollowerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followerViewModel.getGithubFollower()
        initFollowerAdapter()
        observeData()
    }

    private fun initFollowerAdapter() {
        followerAdapter = FollowerAdapter()
        binding.followerListRv.adapter = followerAdapter
    }

    private fun observeData() {
        followerViewModel.followData.observe(viewLifecycleOwner) {
            followerAdapter.submitList(it)
        }
    }
}