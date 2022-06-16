package com.example.room.presentation.follower

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemFollowerListBinding

class FollowerAdapter : ListAdapter<FollowerData, FollowerAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(followerData: FollowerData) {
            binding.followerRecycler = followerData

//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, FollowerDetailActivity::class.java)
//                intent.putExtra("name", followerData.name)
//                intent.putExtra("image", followerData.image)
//                ContextCompat.startActivity(itemView.context, intent, null)
//            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<FollowerData>() {
            override fun areItemsTheSame(
                oldItem: FollowerData,
                newItem: FollowerData
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: FollowerData,
                newItem: FollowerData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}