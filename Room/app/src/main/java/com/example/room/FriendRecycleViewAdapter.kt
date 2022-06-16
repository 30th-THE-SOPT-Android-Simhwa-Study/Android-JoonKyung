package com.example.room

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.room.data.models.types.MBTI
import com.example.room.databinding.ListItemBinding
import com.example.room.db.Friend
import safeValueOf

class FriendRecycleViewAdapter(
    private val clickListener: (Friend) -> Unit
) : RecyclerView.Adapter<FriendViewHolder>() {

    private val friendsList = ArrayList<Friend>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(friendsList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return friendsList.size
    }

    fun setList(friends: List<Friend>) {
        friendsList.clear()
        friendsList.addAll(friends)
    }
}

class FriendViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(friend: Friend, clickListener: (Friend) -> Unit) {
        binding.listItemNameTv.text = friend.name
        binding.listItemEmailTv.text = friend.email
        binding.listItemMbtiTv.text = friend.mbti.toString()

        itemView.setOnClickListener { v ->
            val intent = Intent(v.context, FriendDetailActivity::class.java)
            intent.putExtra("friendInfo", friend)
            v.context.startActivity(intent)
        }

//        binding.listItemLl.setOnClickListener {
//            clickListener(friend)
//        }
    }
}