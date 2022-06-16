package com.example.room.presentation.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemRepositoryListBinding

class RepositoryAdapter :
    ListAdapter<RepositoryData, RepositoryAdapter.FollowerViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemRepositoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FollowerViewHolder(
        private val binding: ItemRepositoryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RepositoryData) {
            binding.repositoryRecycler = data
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RepositoryData>() {
            override fun areItemsTheSame(
                oldItem: RepositoryData,
                newItem: RepositoryData
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: RepositoryData,
                newItem: RepositoryData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}