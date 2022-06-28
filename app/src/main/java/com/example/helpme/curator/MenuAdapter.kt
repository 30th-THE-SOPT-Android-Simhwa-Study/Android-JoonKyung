package com.example.helpme.curator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helpme.databinding.ItemMenuBinding

class MenuAdapter : ListAdapter<MenuData, MenuAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menuData: MenuData) {

            binding.tvMenu.text = menuData.menu

            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, FollowerDetailActivity::class.java)
//                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MenuData>() {
            override fun areItemsTheSame(
                oldItem: MenuData,
                newItem: MenuData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MenuData,
                newItem: MenuData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}