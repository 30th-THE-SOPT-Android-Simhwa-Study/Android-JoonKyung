package com.lee989898.shimhwastudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.shimhwastudy.databinding.ItemFriendListBinding

class FriendListAdapter(
    private val itemClickListener: (Int, String, String) -> Unit
) : ListAdapter<FriendInfo, FriendListAdapter.FriendListViewHolder>(
    ItemDiffCallback<FriendInfo>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.email == new.email }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding = ItemFriendListBinding.inflate(inflater, parent, false)
        return FriendListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
        holder.onBind(getItem(position), itemClickListener)
    }

    class FriendListViewHolder(private val binding: ItemFriendListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            friendInfo: FriendInfo,
            itemClickListener: (Int, String, String) -> Unit
        ) {
            binding.friendInfo = friendInfo
            binding.layoutFriendInfo.setOnClickListener {
                itemClickListener.invoke(friendInfo.id, friendInfo.name, friendInfo.email)
            }
        }
    }
}