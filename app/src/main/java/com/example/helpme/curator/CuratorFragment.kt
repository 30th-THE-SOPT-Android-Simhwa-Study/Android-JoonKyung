package com.example.helpme.curator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.helpme.databinding.FragmentCuratorBinding
import com.google.android.material.tabs.TabLayoutMediator

class CuratorFragment : Fragment() {

    private var _binding: FragmentCuratorBinding? = null
    private val binding
        get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private lateinit var curatorTabViewPagerAdapter: CuratorTabViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCuratorBinding.inflate(layoutInflater, container, false)
        initAdapter()
        initTabLayout()
        return binding.root
    }

    private fun initAdapter() {
        val fragmentList = listOf(MenuFragment(), RecommendFragment())

        curatorTabViewPagerAdapter = CuratorTabViewPagerAdapter(this)
        curatorTabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpCurator.adapter = curatorTabViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("메뉴별 외식 대처법", "주인장 추천 맛집 Best")

        TabLayoutMediator(binding.tabCurator, binding.vpCurator) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}