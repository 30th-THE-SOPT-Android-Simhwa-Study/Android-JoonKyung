package com.example.room.presentation.github

import android.os.Bundle
import android.view.View
import androidx.fragment.app.add
import androidx.fragment.app.replace
import com.example.room.R
import com.example.room.databinding.FragmentGithubBinding
import com.example.room.presentation.follower.FollowerFragment
import com.example.room.presentation.repository.RepositoryFragment
import com.example.room.util.binding.BindingFragment

class GithubFragment : BindingFragment<FragmentGithubBinding>(R.layout.fragment_github) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        buttonSelected(true)

        parentFragmentManager.beginTransaction().add<FollowerFragment>(R.id.profile_fragment_fcv)
            .commit()

        binding.profileFollowerListBt.setOnClickListener {
            buttonSelected(true)
            parentFragmentManager.beginTransaction()
                .replace<FollowerFragment>(R.id.profile_fragment_fcv)
                .commit()
        }
        binding.profileRepositoryListBt.setOnClickListener {
            buttonSelected(false)
            parentFragmentManager.beginTransaction()
                .replace<RepositoryFragment>(R.id.profile_fragment_fcv)
                .commit()
        }
    }

    private fun buttonSelected(select: Boolean) {
        binding.profileFollowerListBt.isSelected = select
        binding.profileRepositoryListBt.isSelected = !select
    }
}