package com.example.room.presentation.repository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.room.R
import com.example.room.databinding.FragmentRepositoryBinding
import com.example.room.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoryFragment :
    BindingFragment<FragmentRepositoryBinding>(R.layout.fragment_repository) {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private val repositoryViewModel by viewModels<RepositoryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        observeRepositoryData()
        initRepositoryAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                repositoryViewModel.getRepository.collect {
                    repositoryAdapter.submitList(it)
                }
            }
        }

    }

//    private fun observeRepositoryData() {
//        repositoryViewModel.repositoryData.observe(viewLifecycleOwner) {
//            repositoryAdapter.submitList(it)
//        }
//    }

    private fun initRepositoryAdapter() {
        repositoryAdapter = RepositoryAdapter()
        binding.repositoryListRv.adapter = repositoryAdapter
    }
}