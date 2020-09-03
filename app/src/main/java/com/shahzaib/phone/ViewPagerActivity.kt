package com.shahzaib.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shahzaib.phone.databinding.ViewPagerActivityBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ViewPagerActivityBinding
    private lateinit var mAdapter: ViewPagerAdapter
    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewPagerActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            if (position == 0){
                tab.text = "Call Logs"
                mAdapter.createFragment(position)
            }
            else if (position == 1){
                tab.text = "Contacts"
                mAdapter.createFragment(position)
            }
        }

        binding.pager.adapter = mAdapter
        tabLayoutMediator.attach()
    }
}