package com.example.room.presentation.repository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.room.R
import com.example.room.databinding.FragmentRepositoryBinding
import com.example.room.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment :
    BindingFragment<FragmentRepositoryBinding>(R.layout.fragment_repository) {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private val repositoryViewModel by viewModels<RepositoryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRepositoryData()
        initRepositoryAdapter()
        repositoryViewModel.getRepositoryData()
    }

    private fun observeRepositoryData() {
        repositoryViewModel.repositoryData.observe(viewLifecycleOwner) {
            repositoryAdapter.submitList(it)
        }
    }

    private fun initRepositoryAdapter() {
        repositoryAdapter = RepositoryAdapter()
        binding.repositoryListRv.adapter = repositoryAdapter
    }
}