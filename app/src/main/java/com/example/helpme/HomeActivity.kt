package com.example.helpme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.helpme.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavi()
        initAdapter()
    }

    private fun initAdapter() {
        val fragmentList = listOf(MapFragment(), CuratorFragment(), ProfileFragment())
        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        homeViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpHome.adapter = homeViewPagerAdapter
    }

    private fun initBottomNavi() {
        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvHome.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvHome.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_map -> {
                    binding.vpHome.currentItem = MAP_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_curator -> {
                    binding.vpHome.currentItem = CURATOR_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpHome.currentItem = PROFILE_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val MAP_FRAGMENT = 0
        const val CURATOR_FRAGMENT = 1
        const val PROFILE_FRAGMENT = 2
    }
}