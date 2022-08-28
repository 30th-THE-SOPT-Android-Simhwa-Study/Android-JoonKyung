package com.lee989898.shimhwastudy.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.shimhwastudy.data.models.db.entity.FriendInfo
import com.lee989898.shimhwastudy.data.models.types.MBTI
import com.lee989898.shimhwastudy.databinding.ItemFriendListBinding
import com.lee989898.shimhwastudy.utils.ItemDiffCallback

class FriendListAdapter(
    private val updateDeleteClickListener: (Int, String, String, MBTI?) -> Unit,
    private val friendDetailClickListener: (FriendInfo?) -> Unit
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
        holder.onBind(getItem(position), updateDeleteClickListener, friendDetailClickListener)
    }

    class FriendListViewHolder(private val binding: ItemFriendListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            friendInfo: FriendInfo,
            updateDeleteClickListener: (Int, String, String, MBTI?) -> Unit,
            friendDetailClickListener: (FriendInfo?) -> Unit
        ) {
            with(binding) {
                this.friendInfo = friendInfo
                btnUpdateDelete.setOnClickListener {
                    updateDeleteClickListener.invoke(
                        friendInfo.id,
                        friendInfo.name,
                        friendInfo.email,
                        friendInfo.mbti
                    )
                }
                layoutFriendInfo.setOnClickListener {
                    friendDetailClickListener(friendInfo)
                }
            }
        }
    }
}