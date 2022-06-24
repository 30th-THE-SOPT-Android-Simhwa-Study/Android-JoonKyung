package com.example.helpme.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helpme.R

class ViewPagerAdapter(explainList: ArrayList<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    var item = explainList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.explain.text = item[position]
    }

    class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.explain_list_item, parent, false)) {

        val explain: TextView = itemView.findViewById(R.id.tv_explain)
    }
}