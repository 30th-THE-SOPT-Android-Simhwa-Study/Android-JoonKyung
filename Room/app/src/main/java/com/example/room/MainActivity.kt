package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.databinding.ActivityMainBinding
import com.example.room.db.Friend
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val friendViewModel by viewModels<FriendViewModel>()
    private lateinit var friendAdapter: FriendRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.friendViewModel = friendViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        friendViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        binding.friendRecyclerView.layoutManager = LinearLayoutManager(this)
        friendAdapter = FriendRecycleViewAdapter { selectedItem: Friend ->
            listItemClicked(selectedItem)
        }
        binding.friendRecyclerView.adapter = friendAdapter
        displayFriendsList()
    }

    private fun displayFriendsList() {
        friendViewModel.getSaveFriends().observe(this, Observer {
            friendAdapter.setList(it)
            friendAdapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(friend: Friend) {
        friendViewModel.initUpdateAndDelete(friend)
    }
}