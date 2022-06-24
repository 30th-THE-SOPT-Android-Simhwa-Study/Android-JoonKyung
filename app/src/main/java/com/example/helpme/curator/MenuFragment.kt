package com.example.helpme.curator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helpme.R
import com.example.helpme.databinding.FragmentCuratorBinding
import com.example.helpme.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding
        get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")

    private lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        initFollowerAdapter()
        return binding.root
    }

    private fun initFollowerAdapter() {
        menuAdapter = MenuAdapter()
        binding.rcvMenu.adapter = menuAdapter
        binding.rcvMenu.layoutManager = GridLayoutManager(activity, 2)

        val menu = listOf(
            MenuData(0, "자장면"), MenuData(1, "짬뽕"),
            MenuData(2, "유산슬"), MenuData(3, "볶음밥"), MenuData(4, "짬짜면"), MenuData(5, "탄탄면")
        )
        menuAdapter.submitList(menu)
    }
}