package com.example.room.presentation.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.databinding.ListItemBinding
import com.example.room.data.local.db.Friend
import com.example.room.presentation.github.GithubFragment
import com.example.room.presentation.home.HomeActivity

class FriendRecycleViewAdapter(
    private val clickListener: (Friend) -> Unit
) : ListAdapter<Friend, FriendRecycleViewAdapter.ViewHolder>(diffUtil) {

    private val friendsList = ArrayList<Friend>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friendsList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return friendsList.size
    }

    fun setList(friends: List<Friend>) {
        friendsList.clear()
        friendsList.addAll(friends)
    }

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: Friend, clickListener: (Friend) -> Unit) {
            binding.friend = friend
//            binding.listItemNameTv.text = friend.name
//            binding.listItemEmailTv.text = friend.email
            binding.listItemMbtiTv.text = friend.mbti.toString()

//        itemView.setOnClickListener { v ->
//            val intent = Intent(v.context, FriendDetailActivity::class.java)
//            intent.putExtra("friendInfo", friend)
//            v.context.startActivity(intent)
//        }

            itemView.setOnClickListener { v ->
                val intent = Intent(v.context, HomeActivity::class.java)
                v.context.startActivity(intent)
            }

//        binding.listItemLl.setOnClickListener {
//            clickListener(friend)
//        }
        }


    }
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Friend>() {
            override fun areItemsTheSame(
                oldItem: Friend,
                newItem: Friend
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: Friend,
                newItem: Friend
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}